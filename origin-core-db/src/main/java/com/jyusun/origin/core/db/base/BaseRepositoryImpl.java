package com.jyusun.origin.core.db.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jyusun.origin.core.common.base.BaseResultCode;
import com.jyusun.origin.core.common.exception.BusinessException;
import com.jyusun.origin.core.common.util.AssemblerUtils;
import com.jyusun.origin.core.db.utils.ConditionUtils;
import com.jyusun.origin.core.db.utils.PageUtils;
import com.jyusun.origin.core.model.page.PageObject;
import com.jyusun.origin.core.model.page.PageQuery;

import java.io.Serializable;

/**
 * 作用描述：
 * - 数据仓储基础服务实现
 *
 * @author JyuSun at 2019/3/29 11:15
 * @version 1.0.0
 */
public abstract class BaseRepositoryImpl<M extends BaseRepoMapper<D>, D extends Model<?>>
        extends ServiceImpl<M, D> implements BaseRepository<D> {

    @Override
    public Boolean unique(Wrapper<D> wrapper, BaseResultCode baseResultCode) {
        if (this.count(wrapper) > 0) {
            throw new BusinessException(baseResultCode);
        }
        return false;
    }

    /**
     * 分页查询
     *
     * @param data      数据对象
     * @param pageQuery 分页条件对象
     * @return <T>
     */
    @Override
    public IPage<D> page(PageQuery pageQuery, D data) {
        return this.page(ConditionUtils.pageInfo(pageQuery), Wrappers.lambdaQuery(data));
    }

    /**
     * 按分页条件查询
     *
     * @param queryWrapper 条件构造器
     * @param pageQuery    条件构造器
     * @return IPage<D>
     */
    @Override
    public IPage<D> page(PageQuery pageQuery, Wrapper<D> queryWrapper) {
        return this.page(ConditionUtils.pageInfo(pageQuery), queryWrapper);
    }


    /**
     * 按分页条件查询
     *
     * @param queryWrapper 条件构造器
     * @param pageQuery    条件构造器
     * @return {@link PageObject} 目标类型
     */
    @Override
    public PageObject<D> pageQuery(PageQuery pageQuery, Wrapper<D> queryWrapper) {
        return PageUtils.dataInfo(this.page(pageQuery, queryWrapper));
    }

    /**
     * 按分页条件查询
     *
     * @param queryWrapper 条件构造器
     * @param pageQuery    条件构造器
     * @return {@link PageObject} 目标类型
     */
    @Override
    public <T> PageObject<T> pageQuery(PageQuery pageQuery, Wrapper<D> queryWrapper, Class<T> target) {
        return PageUtils.dataInfo(this.page(ConditionUtils.pageInfo(pageQuery), queryWrapper), target);
    }

    /**
     * 数据查询
     *
     * @param sid    主键编号
     * @param target 目标转换
     * @return <T>
     */
    @Override
    public <T> T getById(Serializable sid, Class<T> target) {
        return AssemblerUtils.convert(this.getById(sid), target);
    }

    @Override
    public boolean save(Object obj, Class<D> data) {
        return this.save(AssemblerUtils.convert(obj, data));
    }

    @Override
    public boolean updateById(Object obj, Class<D> data) {
        return this.updateById(AssemblerUtils.convert(obj, data));
    }


}
