package com.jyusun.origin.core.common.base;

/**
 *
 * @param <DTO>
 * @param <OBJ>
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
