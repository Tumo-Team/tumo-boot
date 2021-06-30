package cn.tycoding.boot.common.mybatis.config;

import cn.tycoding.boot.common.mybatis.intercept.SqlLogInterceptor;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus插件配置
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    /**
     * SQL 日志打印
     */
    @Bean
    @ConditionalOnProperty(value = {"tumo-boot.mybatis.enable"}, matchIfMissing = true)
    public SqlLogInterceptor sqlLogInterceptor() {
        return new SqlLogInterceptor();
    }
}
