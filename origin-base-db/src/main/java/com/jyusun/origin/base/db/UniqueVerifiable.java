package com.jyusun.origin.base.db;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * 唯一性验证
 * <p>
 * 作用描述：唯一性验证,RPC 传输下禁止使用
 * </p>
 *
 * @author jyusun
 * @date 2020/11/24 17:21
 * @since 1.0.0
 */
public interface UniqueVerifiable<D extends Model<?>> {
    /**
     * 唯一性校验 条件构造器
     *
     * @return {@link LambdaQueryWrapper}
     */
    LambdaQueryWrapper<D> uniqueWrapper();
}
