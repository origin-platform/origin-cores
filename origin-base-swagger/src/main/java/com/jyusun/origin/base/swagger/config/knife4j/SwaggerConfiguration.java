package com.jyusun.origin.base.swagger.config.knife4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Knife4j Config
 *
 * @author jyusun
 */
@Configuration
@EnableSwagger2WebMvc
@EnableConfigurationProperties(SwaggerProperties.class)
@ConditionalOnProperty(prefix = "origin.swagger", name = "enabled", matchIfMissing = true)
public class SwaggerConfiguration {

    private final SwaggerProperties swaggerProperties;

    @Value("${spring.application.name:unknown}")
    private String appName;

    @Autowired
    public SwaggerConfiguration(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean("defaultApi")
    public Docket defaultApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title(swaggerProperties.getTitle())
                        .description(swaggerProperties.getDescription())
                        .termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
                        .contact(swaggerProperties.buildContact())
                        .version(swaggerProperties.getVersion())
                        .build())
                //分组名称
                .groupName(appName)
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }
}