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
@Accessors(chain = true)
@EqualsAndHashCode
@NoArgsConstructor
public class AbstractEvent<T extends Serializable> implements DomainEvent, Serializable {

    private Serializable sid;

    private Long timestamp;

    private T source;

    AbstractEvent(T source) {
        this.source = source;
        this.init();
    }

    private void init() {
        this.sid = 1L;
        this.timestamp = System.currentTimeMillis();
    }


    /**
     * 返回事件的唯一ID
     *
     * @return 事件的唯一ID
     */
    @Override
    public Serializable sid() {
        return this.sid;
    }

    /**
     * 返回事件的产生的时间
     *
     * @return 事件的产生的时间
     */
    @Override
    public long timestamp() {
        return this.timestamp;
    }

    /**
     * 判断领域事件是否一致
     *
     * @param other 另外一个领域事件
     * @return <code>true</code> 如果当前比较的两个领域事件一致返回true
     */
    @Override
    public boolean sameEventAs(DomainEvent other) {
        return false;
    }
}
