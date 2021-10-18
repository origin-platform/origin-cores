package com.jyusun.origin.base.oss.factory;


import com.jyusun.origin.base.oss.factory.handle.OssHandleFactory;
import com.jyusun.origin.core.common.enums.SystemResultEnum;
import com.jyusun.origin.core.common.exception.ServiceException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;

import java.util.Optional;

/**
 * 存取器
 * <p>
 * 作用描述：
 *
 * @author jyusun at 2021/7/12 13:28
 * @since 1.0.0
 */
@AllArgsConstructor
public class OssAccessor implements InitializingBean {

    @Getter(AccessLevel.PROTECTED)
    private final OssHandleFactory ossHandleFactory;

    @Override
    public void afterPropertiesSet() {
        Optional.ofNullable(ossHandleFactory)
                .orElseThrow(() -> new ServiceException(SystemResultEnum.INTERNAL_SERVER_ERROR));
    }

}
