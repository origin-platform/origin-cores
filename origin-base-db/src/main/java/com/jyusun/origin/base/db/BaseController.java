package com.jyusun.origin.base.db;


import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.jyusun.origin.core.common.model.page.PageObject;
import com.jyusun.origin.core.common.model.page.PageQuery;
import com.jyusun.origin.core.common.model.result.AbstractResult;
import com.jyusun.origin.core.common.model.result.ResultFactory;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

/**
 * 请求控制
 * <p>
 * 作用描述：通用请求控制，提供了基础的CRUD操作
 * </p>
 *
 * @author jyusun
 * @date 2020/10/15 17:24
 * @since 1.0.0
 */
public abstract class BaseController<R extends CoreRepository<Domain>, Domain extends Model<?>> {

    @Autowired
    private R originRepository;

    /**
     * 列表分页查询
     *
     * @param pageQuery {@link PageQuery} 分页参数查询对象
     * @param domain      {@link Domain} 系统参数查询对象
     * @return {@link AbstractResult} 响应结果
     */
    @ApiOperation("分页查询：分页条件")
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public AbstractResult<PageObject<Domain>> pageQuery(PageQuery pageQuery, Domain domain) {
        return ResultFactory.data(this.originRepository.pageQuery(pageQuery, domain));
    }


    /**
     * 根据ID查询
     *
     * @param sid {@code Serializable } 主键编号
     * @return {@link AbstractResult} 响应结果
     */
    @ApiOperation("数据查询：主键编号")
    @GetMapping("{sid}")
    @ResponseStatus(code = HttpStatus.OK)
    public AbstractResult<Domain> findById(@PathVariable Serializable sid) {
        return ResultFactory.data(this.originRepository.getById(sid));
    }

    /**
     * 删除数据
     *
     * @param sid {@code Serializable} 主键编号
     * @return {@link AbstractResult<Boolean>} 响应结果
     */
    @ApiOperation("数据删除：主键编号")
    @DeleteMapping("{sid}")
    @ResponseStatus(code = HttpStatus.OK)
    public AbstractResult<Boolean> removeById(@PathVariable Serializable sid) {
        return ResultFactory.status(this.originRepository.removeById(sid));
    }

    /**
     * 新增数据
     *
     * @param domain 数据对象
     * @return {@link AbstractResult<Boolean>} 响应结果
     */
    @ApiOperation("数据新增")
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AbstractResult<Boolean> save(@Validated @RequestBody Domain domain) {
        return ResultFactory.create(this.originRepository.save(domain));
    }

    /**
     * 更新数据
     * <p>
     * 全量更新数据
     * </p>
     *
     * @param domain 数据对象
     * @return {@link AbstractResult<Boolean>} 响应结果
     */
    @ApiOperation("数据更新：主键编号")
    @PutMapping
    public AbstractResult<Boolean> updateById(@Validated @RequestBody Domain domain) {
        return ResultFactory.status(this.originRepository.updateById(domain));
    }
}
