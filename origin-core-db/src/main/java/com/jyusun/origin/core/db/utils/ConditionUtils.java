package com.jyusun.origin.core.db.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jyusun.origin.core.common.exception.BusinessException;
import com.jyusun.origin.core.common.util.ObjectUtil;
import com.jyusun.origin.core.db.constant.DbErrorCode;
import com.jyusun.origin.core.model.page.PageQuery;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 作用描述：
 * - 条件处理
 *
 * @author jyusun at 2020/5/21 17:05
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConditionUtils {

    private static final Integer MAX_LIMIT = 500;

    public static <T> QueryWrapper<T> queryWrapper(T entity) {
        return new QueryWrapper<>(entity);
    }

    /**
     * 作用描述：
     * - 获取分页参数转换
     *
     * @param query {@link PageQuery} 查询参数
     * @return Page<T>
     */
    public static <T> Page<T> pageInfo(PageQuery query) {

        if (query.getLimit() > MAX_LIMIT) {
            throw new BusinessException(DbErrorCode.MORE_THAN_THE_MAXIMUM);
        }
        Page<T> page = new Page<>(query.getPage(), query.getLimit());
        String[] arrayAsc = query.getAscs();
        if (ObjectUtil.isNotEmpty(arrayAsc)) {
            page.addOrder(OrderItem.ascs(arrayAsc));
        }
        String[] arrayDesc = query.getDescs();
        if (ObjectUtil.isNotEmpty(arrayDesc)) {
            page.addOrder(OrderItem.descs(arrayDesc));
        }
        return page;
    }
}
