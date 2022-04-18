package com.jyusun.origin.core.logger.common.enums;

import com.jyusun.origin.core.common.model.BaseKvEnum;
import lombok.AllArgsConstructor;

/**
 * 日志操作 类型
 *
 * @author jyusun
 */
@AllArgsConstructor
public enum OperTypeEnum implements BaseKvEnum {

    /**
     * -1-未知类型， 1-数据新增，2-数据更新，3-数据删除，4-用户登录，5-数据查询，6-数据列表，7-分页查询，10-文件上传，11-文件导入，12-文件导出
     */
    DEFAULT("-1", "未知类型"),
    SAVE("1", "数据新增"),
    UPDATE("2", "数据更新"),
    DELETE("3", "数据删除"),
    LOGIN("4", "用户登录"),
    FIND("5", "数据查询"),
    LIST("6", "数据列表"),
    PAGE("7", "分页查询"),
    UPLOAD("10", "文件上传"),
    IMPORT("11", "文件导入"),
    EXPORT("12", "文件导出");

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
