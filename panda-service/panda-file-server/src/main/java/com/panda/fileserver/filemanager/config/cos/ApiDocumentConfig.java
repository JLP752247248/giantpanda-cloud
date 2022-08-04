package com.panda.fileserver.filemanager.config.cos;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-01  11:19
 * @Description: api文档配置Knife4j
 * @Version: 1.0
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class ApiDocumentConfig {
    public Docket restApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .description("描述")
                        .version("1.0")
                        .build())
                .groupName("1.0版本")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.panda.fileserver.filemanager.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
