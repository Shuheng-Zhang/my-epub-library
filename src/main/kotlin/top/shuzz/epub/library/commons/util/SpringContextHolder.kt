package top.shuzz.epub.library.commons.util

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

/**
 * Spring Context 上下文工具
 * @author heng
 * @since 2022/4/12
 */
@Component
class SpringContextHolder: ApplicationContextAware {

    companion object {
        private lateinit var applicationContext: ApplicationContext

        fun <T> getBean(requiredType: Class<T>): T {
            return applicationContext.getBean(requiredType)
        }

        fun getBean(beanName: String) : Any {
            return applicationContext.getBean(beanName)
        }
    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        SpringContextHolder.applicationContext = applicationContext
    }

}