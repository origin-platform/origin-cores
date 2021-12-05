package com.jyusun.origin.base.swagger.config.knife4j;


import com.jyusun.origin.core.common.constant.SystemConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

/**
 * swagger 属性配置
 *
 * @author jyusun
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "origin-system.swagger")
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


    public ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title(this.getTitle())
                .description(this.getDescription())
                .termsOfServiceUrl(this.getTermsOfServiceUrl())
                .contact(ContactInfo.creator()
                        .name(contact.getName())
                        .url(contact.getUrl())
                        .email(contact.getEmail()).build())
                .version(this.getVersion())
                .build();
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

        public static ContactBuilder creator() {
            return new ContactBuilder();
        }

        public static class ContactBuilder {

            @ApiModelProperty("联系人")
            private String name;
            @ApiModelProperty("URL")
            private String url;
            @ApiModelProperty("联络邮箱")
            private String email;

            private ContactBuilder name(String name) {
                this.name = name;
                return this;
            }

            private ContactBuilder url(String url) {
                this.url = url;
                return this;
            }

            private ContactBuilder email(String email) {
                this.email = email;
                return this;
            }

            private Contact build() {
                return new Contact(name, url, email);
            }
        }
    }


}
