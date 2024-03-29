package com.panda.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-09  09:25
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
public class GatewayConfiguration {

    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider, ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    /**
     * 配置限流的异常处理器:SentinelGatewayBlockExceptionHandler
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    /**
     * 配置限流过滤器
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    /**
     * 配置初始化的限流参数
     * 用于指定资源的限流规则.
     * 1.资源名称 (路由id)
     * 2.配置统计时间
     * 3.配置限流阈值
     */
    @PostConstruct
    public void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();
//		rules.add(new GatewayFlowRule("product-service")
//				.setCount(1)
//				.setIntervalSec(1)
//		);
        rules.add(new GatewayFlowRule("business_api")
                .setCount(10).setIntervalSec(1)
        );

        GatewayRuleManager.loadRules(rules);

        List<DegradeRule> rules1 = new ArrayList<>();
        // ---------------熔断-降级配置-------------
        DegradeRule degradeRule = new DegradeRule("business_api") // 资源名称
                .setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT) // 异常比率模式(秒级)
                .setCount(1)
                .setMinRequestAmount(1)
                .setStatIntervalMs(5000)
                .setTimeWindow(1); // 熔断降级时间(10s)
        rules1.add(degradeRule);

        // 加载规则.
        DegradeRuleManager.loadRules(rules1);
    }

    /**
     * 自定义API限流分组
     * 1.定义分组
     * 2.对小组配置限流规则
     */
    @PostConstruct
    private void initCustomizedApis() {
        Set<ApiDefinition> definitions = new HashSet<>();
        //限流小组1 api1
        ApiDefinition api1 = new ApiDefinition("business_api")
                .setPredicateItems(new HashSet<ApiPredicateItem>() {{
                    add(new ApiPathPredicateItem().setPattern("/business/**").
                            //已/product-service/product/开都的所有url
                                    setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
                }});
        //限流小组2 api2
        ApiDefinition api2 = new ApiDefinition("order_api")
                .setPredicateItems(new HashSet<ApiPredicateItem>() {{
                    add(new ApiPathPredicateItem().setPattern("/order-service/order")); //完全匹配/order-service/order 的url
                }});
        //将小组通知给sentinel
        definitions.add(api1);
        definitions.add(api2);

        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
        //然后在initGatewayRules方法中rule.add添加限流规则
    }

    /**
     * 自定义限流处理器
     */
    @PostConstruct
    public void initBlockHandlers() {
        BlockRequestHandler blockHandler = new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                Map map = new HashMap();
                if (throwable instanceof DegradeException) {//降级、熔断
                    map.put("status", 601);
                    map.put("message", "服务被熔断了!");
                } else if (throwable instanceof FlowException) {
                    map.put("status", 602);
                    map.put("message", "服务被限流了!");
                } else {
                    map.put("status", 603);
                    map.put("message", "Blocked by Sentinel (flow limiting)");
                }
                return ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(BodyInserters.fromObject(map));
            }
        };
        GatewayCallbackManager.setBlockHandler(blockHandler);
    }
}