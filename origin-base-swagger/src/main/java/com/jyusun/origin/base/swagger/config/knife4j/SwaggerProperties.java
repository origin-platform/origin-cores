package com.jyusun.origin.base.swagger.config.knife4j;


import com.jyusun.origin.core.common.constant.SystemConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.service.Contact;

/**
 * swagger 属性配置
 *
 * @author jyusun
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "origin.swagger")
public class SwaggerProperties {
    /**
     * isEnabled：true or false
     */
    private Boolean enabled = true;
    /**
     * title
     */
    private String title = "Origin Platform";
    /**
     * description
     */
    private String description = "Description Project";
    /**
     * termsOfServiceUrl
     */
    private String termsOfServiceUrl;
    /**
     * contact
     */
    private SwaggerProperties.ContactInfo contact = new SwaggerProperties.ContactInfo();
    /**
     * license
     */
    private String license;
    /**
     * licenseUrl
     */
    private String licenseUrl;
    /**
     * version
     */
    private String version = "1.0.0";
    /**
     * base package
     */
    private String basePackage = SystemConstant.BASE_WEB;

    /**
     * 构建联络方式
     *
     * @return {@link Contact}
     */
    Contact buildContact() {
        return contactBuilder().name(contact.getName()).url(contact.getUrl()).email(contact.getEmail()).build();
    }

    /**
     * ContactBuilder
     *
     * @return
     */
    private SwaggerProperties.ContactBuilder contactBuilder() {
        return new SwaggerProperties.ContactBuilder();
    }

    @Setter
    @Getter
    private static class ContactInfo {
        /**
         * name
         */
        @ApiModelProperty("联系人")
        private String name;
        /**
         * url
         */
        @ApiModelProperty("URL")
        private String url;
        /**
         * email
         */
        @ApiModelProperty("联络邮箱")
        private String email;

    }

    private static class ContactBuilder {

        @ApiModelProperty("联系人")
        private String name;
        @ApiModelProperty("URL")
        private String url;
        @ApiModelProperty("联络邮箱")
        private String email;

        private SwaggerProperties.ContactBuilder name(String name) {
            this.name = name;
            return this;
        }

        private SwaggerProperties.ContactBuilder url(String url) {
            this.url = url;
            return this;
        }

        private SwaggerProperties.ContactBuilder email(String email) {
            this.email = email;
            return this;
        }

        private Contact build() {
            return new Contact(name, url, email);
        }

    }


}
