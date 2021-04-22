package com.jyusun.origin.core.db.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * 作用描述：
 * - 基础仓储映射
 *
 * @author jyusun at 2020/2/17 12:18
 * @since 1.0.0
 */
public interface BaseRepoMapper<D extends Model<?>> extends BaseMapper<D> {
}
