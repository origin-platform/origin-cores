package com.jyusun.origin.core.model.domain.value;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 作用描述： - 基础值对象
 *
 * @author jyusun at 2020/1/7 15:11
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public abstract class BaseValue implements Serializable {

    private static final long serialVersionUID = 1L;
}
