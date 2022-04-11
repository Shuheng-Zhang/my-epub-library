package top.shuzz.epub.library.commons.config

import cn.hutool.core.util.StrUtil
import cn.hutool.log.LogFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

/**
 * 配置异步任务执行器
 * @author heng
 * @since 2022/4/11
 */
@Configuration
@EnableAsync
class AsyncTaskExecutorConfig {

    companion object {
        /**
         * 默认值-核心线程数
         */
        private const val CORE_POOL_SIZE_DEFAULT = 4
        /**
         * 默认值-最大线程数
         */
        private const val MAX_POOL_SIZE_DEFAULT = 8
        /**
         * 默认值-任务队列容量
         */
        private const val QUEUE_CAPACITY_DEFAULT = 32
        /**
         * 默认值-线程保活时长(秒)
         */
        private const val KEEP_ALIVE_SECONDS_DEFAULT = 60
        /**
         * 默认值-线程前缀名
         */
        private const val PREFIX_NAME_DEFAULT = "async-task-executor-"
    }

    private val log = LogFactory.get()

    @Value("\${async-task-executor.core-pool-size}")
    private lateinit var corePoolSize: String
    @Value("\${async-task-executor.max-pool-size}")
    private lateinit var maxPoolSize: String
    @Value("\${async-task-executor.queue-capacity}")
    private lateinit var queueCapacity: String
    @Value("\${async-task-executor.keep-alive-seconds}")
    private lateinit var keepAliveSeconds: String
    @Value("\${async-task-executor.prefix-name}")
    private lateinit var prefixName: String

    @Bean(value = ["async-task-executor"])
    fun asyncTaskExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = this.checkIntegerValue(corePoolSize, CORE_POOL_SIZE_DEFAULT)
        executor.maxPoolSize = this.checkIntegerValue(maxPoolSize, MAX_POOL_SIZE_DEFAULT)
        executor.keepAliveSeconds = this.checkIntegerValue(keepAliveSeconds, KEEP_ALIVE_SECONDS_DEFAULT)
        executor.setQueueCapacity(this.checkIntegerValue(queueCapacity, QUEUE_CAPACITY_DEFAULT))
        if (StrUtil.isEmptyIfStr(prefixName)) {
            executor.setThreadNamePrefix(PREFIX_NAME_DEFAULT)
        } else {
            executor.setThreadNamePrefix(prefixName)
        }

        executor.initialize()

        log.info("Created AsyncTaskExecutor: [corePoolSize=${executor.corePoolSize}, maxPoolSize=${executor.maxPoolSize}, keepAliveSeconds=${executor.keepAliveSeconds}]")

        return executor
    }

    /**
     * 检查并将转换配置参数的值
     * @param srcVal 配置源的值
     * @param defaultValue 默认值(当源值不存在或转换出错时使用)
     */
    private fun checkIntegerValue(srcVal : String?, defaultValue: Int): Int {
        if (StrUtil.isEmptyIfStr(srcVal)) {
            return defaultValue
        }
        return try {
            Integer.parseInt(srcVal)
        } catch (e : Exception) {
            defaultValue
        }
    }
}