package com.jyusun.origin.core.db.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jyusun.origin.core.common.util.BeanUtil;
import com.jyusun.origin.core.model.page.PageObject;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.Collections;

/**
 * <p>
 * 操作Page工具类
 * </p>
 *
 * @author Sun
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PageUtils {

    /**
     * 获取分页返回值，传入处理后的集合数据
     *
     * @param page 分页信息
     * @param <T>  分页限定类型
     * @return <T, R> PageDTO<R>
     */
    public static <T> PageObject<T> dataInfo(IPage<T> page) {
        PageObject<T> pageObject = new PageObject<>();
        pageObject.setRows(CollectionUtils.isEmpty(page.getRecords()) ?
                Collections.emptyList() : page.getRecords());
        pageObject.setLimit(page.getSize());
        pageObject.setPage(page.getCurrent());
        pageObject.setTotal(page.getTotal());
        pageObject.setPages(page.getPages());
        return pageObject;
    }

    /**
     * 获取分页返回值，传入处理后的集合数据
     *
     * @param page 分页信息
     * @param <T>  分页限定类型
     * @return <T, R> PageDTO<R>
     */
    public static <T, R> PageObject<R> dataInfo(IPage<T> page, Class<R> clazz) {
        PageObject<R> pageObject = new PageObject<>();
        pageObject.setRows(CollectionUtils.isEmpty(page.getRecords()) ?
                Collections.emptyList() : BeanUtil.copyList(page.getRecords(), clazz));
        pageObject.setLimit(page.getSize());
        pageObject.setPage(page.getCurrent());
        pageObject.setTotal(page.getTotal());
        pageObject.setPages(page.getPages());

        return pageObject;
    }
}
