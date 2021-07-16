package com.jyusun.origin.base.db.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.jyusun.origin.core.common.constant.SystemConstant;
import com.jyusun.origin.secure.UserInfo;
import com.jyusun.origin.secure.common.util.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;

/**
 * 作用描述：
 * - MybatisPlus 配置 （注意控制扫描范围，否则容易引起重复注入问题）
 *
 * @author jyusun at 2020/1/29 12:54
 * @since 1.0.0
 */
@Slf4j
@Configuration
@MapperScan(SystemConstant.BASE_MAPPER)
@EnableTransactionManagement
public class MybatisPlusConfiguration {

    /**
     * 乐观锁插件
     *
     * @return OptimisticLockerInterceptor
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 分页器插件
     *
     * @return PaginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;
    }

    /**
     * 自动填充配置
     */
    @Component
    public static class MyMetaObjectHandler implements MetaObjectHandler {
        /**
         * 数据插入时 填充
         *
         * @param metaObject 元数据对象
         */
        @Override
        public void insertFill(MetaObject metaObject) {
            UserInfo userInfo = SecureUtil.getUser();
            log.info("start insert fill ....");
            this.fillStrategy(metaObject, "creator", userInfo.getUserId());
            this.fillStrategy(metaObject, "updateMan", userInfo.getUserId());

            this.fillStrategy(metaObject, "createTime", LocalDateTime.now());
            this.fillStrategy(metaObject, "updateTime", LocalDateTime.now());
        }

        /**
         * 数据更新时 填充
         *
         * @param metaObject 元数据对象
         */
        @Override
        public void updateFill(MetaObject metaObject) {
            UserInfo userInfo = SecureUtil.getUser();
            log.info("start update fill ....");
            this.fillStrategy(metaObject, "updateMan", userInfo.getUserId());
            this.fillStrategy(metaObject, "updateTime", LocalDateTime.now());
        }
    }


}
