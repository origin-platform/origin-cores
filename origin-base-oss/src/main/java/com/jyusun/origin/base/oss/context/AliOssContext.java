package com.jyusun.origin.base.oss.context;

import com.aliyun.oss.OSS;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.factory.rule.OssRule;
import lombok.Getter;

@Getter
public class AliOssContext implements OssContext {

    private final OSS oss;
    private final OssProperties ossProperties;
    private final OssRule ossRule;

    public AliOssContext(OSS oss, OssProperties ossProperties, OssRule ossRule) {
        this.oss = oss;
        this.ossProperties = ossProperties;
        this.ossRule = ossRule;
    }

    public static AliOssContextBuilder builder() {
        return new AliOssContextBuilder();
    }

    public static class AliOssContextBuilder {

        private OSS oss;
        private OssProperties ossProperties;
        private OssRule ossRule;

        public AliOssContextBuilder ossProperties(OssProperties ossProperties) {
            this.ossProperties = ossProperties;
            return this;
        }

        public AliOssContextBuilder ossRule(OssRule ossRule) {
            this.ossRule = ossRule;
            return this;
        }

        public AliOssContextBuilder oss(OSS oss) {
            this.oss = oss;
            return this;
        }

        public AliOssContext build() {
            return new AliOssContext(oss, ossProperties, ossRule);
        }
    }
}