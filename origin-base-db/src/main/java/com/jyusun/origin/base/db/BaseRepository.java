package com.jyusun.origin.base.db;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jyusun.origin.core.common.base.BaseResultCode;
import com.jyusun.origin.core.model.page.PageObject;
import com.jyusun.origin.core.model.page.PageQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.core.convert.converter.Converter;

import java.io.Serializable;

/**
 * 作用描述：
 * - 数据仓储服务对象接口
 *
 * @author JyuSun at 2019/3/29 11:15
 * @version 1.0.0
 */
public interface BaseRepository<D extends Model<?>> extends IService<D> {

    /**
     * 唯一校验
     *
     * @param wrapper        {@link Wrapper}条件构造器
     * @param baseResultCode {@link BaseResultCode} 响应信息
     * @return false 不存在
     */
    Boolean unique(Wrapper<D> wrapper, BaseResultCode baseResultCode);

    /**
     * 按分页条件查询
     *
     * @param data      查询数据对象
     * @param pageQuery 条件构造器
     * @return <T>
     */
    IPage<D> page(PageQuery pageQuery, D data);

    /**
     * 按分页条件查询
     *
     * @param queryWrapper 条件构造器
     * @param pageQuery    条件构造器
     * @return <T>
     */
    IPage<D> page(PageQuery pageQuery, @Param("ew") Wrapper<D> queryWrapper);

    /**
     * 按分页条件查询
     *
     * @param pageQuery    分页查询条件
     * @param queryWrapper 条件构造器
     * @return <T>
     */
    PageObject<D> pageQuery(PageQuery pageQuery, Wrapper<D> queryWrapper);

    /**
     * 分页查询
     *
     * @param pageQuery    分页查询条件
     * @param queryWrapper 条件构造器
     * @param target       目标转换
     * @return <T>
     */
    <T extends Serializable> PageObject<T> pageQuery(PageQuery pageQuery, @Param("ew") Wrapper<D> queryWrapper, Class<T> target);

    /**
     * 分页查询
     *
     * @param pageQuery    分页查询条件
     * @param queryWrapper 条件构造器
     * @param target       目标转换
     * @return <T>
     */
    <T extends Converter<D, T> & Serializable> PageObject<T> pageConvert(PageQuery pageQuery, @Param("ew") Wrapper<D> queryWrapper,
                                                                       Class<T> target);

    /**
     * 数据查询
     *
     * @param sid    主键编号
     * @param target 目标转换
     * @return <T>
     */
    <T> T getById(Serializable sid, Class<T> target);


    /**
     * 数据保存
     *
     * @param obj  命令对象
     * @param data 数据对象
     * @return
     */
    boolean save(Object obj, Class<D> data);

    /**
     * 数据更新
     *
     * @param obj  命令对象
     * @param data 数据对象
     * @return
     */
    boolean updateById(Object obj, Class<D> data);
}
