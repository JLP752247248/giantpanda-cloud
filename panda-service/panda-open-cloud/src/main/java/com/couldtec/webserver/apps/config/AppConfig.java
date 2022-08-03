package com.couldtec.webserver.apps.config;

import org.slf4j.Logger;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;

import com.couldtec.webserver.bootstrap.filter.HTTPConfigFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
/**
 * 配置
 * @author jlp
 *
 */
@Configuration
@ImportResource(value={"classpath:applicationContext.xml"})
public class AppConfig {
	private static final Logger logger=org.slf4j.LoggerFactory.getLogger(AppConfig.class);
	/**
	 * messageconvertor设置为FastJson
	 * @return
	 */
	@Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
	   logger.info("init messageconverters:FastJsonHttpMessageConverter");
       FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
       FastJsonConfig fastJsonConfig = new FastJsonConfig();
       fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
       fastConverter.setFastJsonConfig(fastJsonConfig);
       HttpMessageConverter<?> converter = fastConverter;
       StringHttpMessageConverter strHC=new StringHttpMessageConverter();
       MarshallingHttpMessageConverter mHC=new MarshallingHttpMessageConverter();
       return new HttpMessageConverters(converter,strHC,mHC);
    }
    /**
	 * http请求过滤初始化
	 * @param ctx
	 * @return
	 */
    @Bean
    public FilterRegistrationBean initHttp(ApplicationContext ctx){
    	logger.info("FilterRegistrationBean,filterHttp");
    	FilterRegistrationBean registration = new FilterRegistrationBean(new HTTPConfigFilter());
        return registration;
    }
    
}
