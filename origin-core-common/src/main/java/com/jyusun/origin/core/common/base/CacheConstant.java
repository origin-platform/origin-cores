package com.jyusun.origin.core.common.base;

import com.jyusun.origin.core.common.util.StringUtil;

/**
 * 系统字典类型常量
 * <p>
 * 作用描述：REDIS 缓存常量
 * </p>
 *
 * @author jyusun
 * @date 2020/11/23 17:29
 * @since 1.0.0
 */
public interface CacheConstant extends BaseConstants {

    /**
     * 缓存前缀
     */
    String REDIS_PREFIX = "admins";

    /**
     * 字典类型-字典
     */
    String TYPE_DEFAULT = REDIS_PREFIX + StringUtil.COLON + "dicts";

    /**
     * 字典类型-系统用户
     */
    String TYPE_USER = REDIS_PREFIX + StringUtil.COLON + "users";

    /**
     * 字典类型-参数
     */
    String TYPE_PARAM = REDIS_PREFIX + StringUtil.COLON + "params";

    /**
     * 字典：集合数据
     */
    String DICT_LIST = TYPE_DEFAULT + StringUtil.COLON + "list";

    /**
     * 字典：单个值
     */
    String DICT_VALUE = TYPE_DEFAULT + StringUtil.COLON + "value";
}
