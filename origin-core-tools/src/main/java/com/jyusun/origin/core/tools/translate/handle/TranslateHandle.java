package com.jyusun.origin.core.tools.translate.handle;

import com.jyusun.origin.core.common.util.SpringUtil;
import com.jyusun.origin.core.tools.translate.annotation.TranslateParam;
import com.jyusun.origin.core.tools.translate.provider.TranslateProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 作用描述： - 翻译缓存
 *
 * @author jyusun at 2019/9/23 17:07
 * @since 1.0.0
 */
@Slf4j
@Component
public class TranslateHandle {


    public String queryTranslate(TranslateParam param, Object propertyValue) {
        String split = param.split();
        TranslateProvider translateProvider = SpringUtil.getBean(param.source());
        StringBuffer label = new StringBuffer();
        if (StringUtils.isEmpty(split)) {
            String labelValue = translateProvider.findLabel(param.type(), propertyValue);
            label.append(labelValue);
        } else {
            String[] values = String.valueOf(propertyValue).split(split);
            Arrays.stream(values).forEach(value -> {
                String labelValue = translateProvider.findLabel(param.type(), value);
                label.append(StringUtils.isNotEmpty(labelValue) ? labelValue : value).append(split);
            });
        }
        return label.toString();
    }

}
