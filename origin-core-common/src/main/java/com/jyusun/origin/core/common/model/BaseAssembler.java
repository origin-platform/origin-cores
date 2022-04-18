package com.jyusun.origin.core.common.model;

/**
 * 转换接口
 *
 * @param <DTO>
 * @param <OBJ>
 * @author jyusun at 2021-11-21 18:57:35
 * @since 1.0.0
 */
public interface BaseAssembler<DTO, OBJ> {

    /**
     * 数据对象 转 数据传输对象
     *
     * @param obj 数据对象
     * @return 数据传输对象
     */
    DTO toDto(OBJ obj);

    /**
     * 数据传输对象 转 数据对象
     *
     * @param dto 数据传输对象
     * @return 数据对象
     */
    OBJ toObj(DTO dto);

}
