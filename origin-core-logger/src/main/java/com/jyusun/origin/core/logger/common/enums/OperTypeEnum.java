package com.jyusun.origin.core.logger.common.enums;

import com.jyusun.origin.core.common.base.BaseEnum;
import lombok.AllArgsConstructor;

/**
 * 日志操作 类型
 *
 * @author jyusun
 */
@AllArgsConstructor
public enum OperTypeEnum implements BaseEnum {

    /**
     * 1-数据新增，2-数据更新，3-数据删除，4-用户登录，5-数据查询，6-数据列表，7-分页查询，9-未知类型
     */
    DEFAULT("9", "未知类型"),
    SAVE("1", "数据新增"),
    UPDATE("2", "数据更新"),
    DELETE("3", "数据删除"),
    LOGIN("4", "用户登录"),
    FIND("5", "数据查询"),
    LIST("6", "数据列表"),
    PAGE("7", "分页查询");

    private final String code;
    private final String desc;

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String desc() {
        return this.desc;
    }
}
