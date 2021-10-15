package com.jyusun.origin.base.oss.factory.props;

import com.jyusun.origin.base.oss.config.props.OssProperties;
import com.jyusun.origin.base.oss.factory.rule.OssRule;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 对象存储工厂
 *
 * @author jyusun at 2021-10-8 15:53:32
 * @since 1.0.0
 */
@Getter
@Setter
@Accessors(chain = true)
public abstract class AbstractPropsFactory {

    /**
     * 对象存储属性
     */
    private OssProperties ossProperties;

    /**
     * 对象存储规则
     */
    private OssRule ossRule;


}
