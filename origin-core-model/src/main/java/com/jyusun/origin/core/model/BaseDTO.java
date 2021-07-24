package com.jyusun.origin.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * 作用描述：基础数据传输对象
 *
 * @author jyusun
 * @date 2020/5/18 12:01
 * @since 1.0.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
}
