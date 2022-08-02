package com.couldtec.webserver.apps.config.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * swagger文档
 * @author jlp
 *
 */
@EnableSwagger2  
@Configuration  
public class RestApiConfig implements WebMvcConfigurer {
  
    @Bean  
    public Docket createRestApi() {  
        return new Docket(DocumentationType.SWAGGER_2)  
                .apiInfo(apiInfo())  
                .select()  
                .apis(RequestHandlerSelectors.basePackage("com.ai.itms"))  
                .paths(PathSelectors.any())  
                .build();  
    }  
  
    private ApiInfo apiInfo() {  
        return new ApiInfoBuilder()  
                .title("翼翮回调云网关原子能力")  
                .termsOfServiceUrl("http://asiainfo.com")  
                .version("1.1")  
                .build();  
    }  
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  registry.addResourceHandler("/**").addResourceLocations("/");
	  registry.addResourceHandler("/css/**").addResourceLocations("classpath:/templates/css/");
	  registry.addResourceHandler("/js/**").addResourceLocations("classpath:/templates/js/");
	  registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
	  registry.addResourceHandler("/api-docs/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

	 
    }
} 