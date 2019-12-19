package com.ruiqing.util;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket platformApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .forCodeGeneration(true)
                .globalOperationParameters(createGlobalParams())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.crc.imsdl.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Ripples-API").description("©2018 Copyright. Powered By Ripples Team.")
                // .termsOfServiceUrl("")
                .contact(new Contact("Ripples Team", "", "RipplesTeam@crc.com.hk")).license("China Resource Group Science And Information Department").version("2.0").build();
    }

    private List<Parameter> createGlobalParams(){
        List<Parameter> aParameters = new ArrayList<Parameter>();

        //可以添加多个header或参数
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder
                .parameterType("header") //参数类型支持header, cookie, body, query etc
                .name("crctoken") //参数名
                .defaultValue("crctoken#000") //默认值
                .description("header中crctoken字段")
                .modelRef(new ModelRef("string"))//指定参数值的类型
                .required(false).build(); //非必需，这里是全局配置，然而在登陆的时候是不用验证的

        aParameters.add(aParameterBuilder.build());

        return aParameters;
    }
}
