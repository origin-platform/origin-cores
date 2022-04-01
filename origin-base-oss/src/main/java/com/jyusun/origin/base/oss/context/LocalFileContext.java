package com.jyusun.origin.base.oss.context;

import com.aliyun.oss.OSS;
import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.factory.rule.OssRule;
import lombok.Getter;

@Getter
public class LocalFileContext implements OssContext {

    private final OssProperties ossProperties;
    private final OssRule ossRule;

    public LocalFileContext(OssProperties ossProperties, OssRule ossRule) {
        this.ossProperties = ossProperties;
        this.ossRule = ossRule;
    }

    public static LocalContextBuilder builder() {
        return new LocalContextBuilder();
    }

    public static class LocalContextBuilder {

        private OSS oss;
        private OssProperties ossProperties;
        private OssRule ossRule;

        public LocalContextBuilder ossProperties(OssProperties ossProperties) {
            this.ossProperties = ossProperties;
            return this;
        }

        public LocalContextBuilder ossRule(OssRule ossRule) {
            this.ossRule = ossRule;
            return this;
        }

        public LocalContextBuilder oss(OSS oss) {
            this.oss = oss;
            return this;
        }

        public LocalFileContext build() {
            return new LocalFileContext(ossProperties, ossRule);
        }
    }
}