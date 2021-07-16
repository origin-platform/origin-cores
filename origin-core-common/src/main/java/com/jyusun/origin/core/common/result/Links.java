package com.jyusun.origin.core.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 链接信息
 * <p>
 * 作用描述：链接信息
 *
 * @author jyusun
 * @date 2020/12/3 17:48
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel("链接对象")
@NoArgsConstructor
public class Links implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("请求地址")
    private String self;

    public Links(String self) {
        this.self = self;
    }

    /**
     * 创建Links构建对象
     *
     * @return
     */
    public static LinksBuilder builder() {
        return new LinksBuilder();
    }

    @NoArgsConstructor
    public static class LinksBuilder {

        @ApiModelProperty("请求地址")
        private String self;

        /**
         * 请求地址赋值
         *
         * @param self
         * @return
         */
        public LinksBuilder self(String self) {
            this.self = self;
            return this;
        }

        // 构建链接实体
        public Links build() {
            return new Links(self);
        }
    }


}
