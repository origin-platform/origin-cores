package com.jyusun.origin.core.model.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 作用描述：领域实体对象
 * </p>
 *
 * @author jyusun at 2020/6/30 16:47
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public abstract class BaseDomain implements Serializable {

    private static final long serialVersionUID = 1L;

}
