package top.shuzz.epub.library.commons.config

import com.baomidou.mybatisplus.annotation.DbType
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * MybatisPlus 配置
 * @author heng
 * @since 2022/4/10
 */
@Configuration
class MyBatisConfig {

    /**
     * 启用 MybatisPlus 分页
     */
    @Bean
    fun mybatisPlusInterceptor(): MybatisPlusInterceptor {
        val interceptor = MybatisPlusInterceptor()
        interceptor.addInnerInterceptor(PaginationInnerInterceptor(DbType.MARIADB))

        return interceptor
    }
}