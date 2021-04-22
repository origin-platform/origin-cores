package com.jyusun.origin.core.model.domain.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 作用描述： - 领域事件 基础扩展
 *
 * @author jyusun at 2020/1/7 13:23
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
public class AbstractEvent<T> implements Serializable {

	private Serializable id;

	private Long timestamp;

	private String source;

	private T data;

}
