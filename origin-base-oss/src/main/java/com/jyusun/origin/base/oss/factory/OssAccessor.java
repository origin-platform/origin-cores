package com.jyusun.origin.base.oss.factory;


import com.aliyun.oss.OSSClient;
import com.jyusun.origin.base.oss.rule.OssRule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;

/**
 * 存取器
 * <p>
 * 作用描述：
 *
 * @author jyusun
 * @date 2021/7/12 13:28
 * @since 1.0.0
 */
public class OssAccessor implements InitializingBean {

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private OssFactory ossFactory;


    @Override
    public void afterPropertiesSet() {

    }

}
