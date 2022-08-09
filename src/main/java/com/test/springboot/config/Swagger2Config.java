package com.test.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("AOP")
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test.springboot.controller.aop"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket buildDocket2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("FIRST")
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test.springboot.controller.first"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket buildDocket3() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("JSON")
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test.springboot.controller.json"))
                .paths(PathSelectors.any())
                .build();
    }


    @Bean
    public Docket buildDocket4() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("REDIS")
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test.springboot.controller.redis"))
                .paths(PathSelectors.any())
                .build();
    }


    @Bean
    public Docket buildDocket5() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("STUDENT")
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test.springboot.controller.student"))
                .paths(PathSelectors.any())
                .build();
    }


    @Bean
    public Docket buildDocket6() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("ALL")
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.test.springboot.controller"))
                .paths(PathSelectors.any())
                .build();
    }



    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("系统RESTful API文档")
                .contact(new Contact("Kent", "http://localhost:8081/swagger-ui.html", "***@126.com"))
                .version("1.0")
                .build();
    }
}
