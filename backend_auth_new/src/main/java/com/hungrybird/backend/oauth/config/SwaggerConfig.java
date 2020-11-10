package com.hungrybird.backend.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any()) // 현재 RequestMapping으로 할당된 모든 URL 리스트를 추출
//                .paths(PathSelectors.ant("/api/**")) // 그중 /api/** 인 URL들만 필터링
//                .build();
//    }

    @Bean
    public Docket productApi() {
//        ParameterBuilder aParameterBuilder = new ParameterBuilder();
//        aParameterBuilder.name("Authorization") //헤더 이름
//                .description("Access Tocken") //설명
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(false)
//                .build();

//        List<Parameter> aParameters = new ArrayList<>();
//        aParameters.add(aParameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
//                .globalOperationParameters(aParameters)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hungrybird.backend.oauth"))
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }


    private ApiInfo metaInfo() {
        return new ApiInfoBuilder()
                .description("Backend API For the Auth/User Service")
                .title("Auth/User API")
                .version("Unreleased [WIP]")
                .build();
    }
}
