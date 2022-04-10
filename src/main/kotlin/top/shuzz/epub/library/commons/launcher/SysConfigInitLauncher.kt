package top.shuzz.epub.library.commons.launcher

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import top.shuzz.epub.library.modular.service.SysConfigService
import javax.annotation.Resource
import org.springframework.context.annotation.Lazy
import org.springframework.core.annotation.Order
import top.shuzz.epub.library.commons.util.SysConfigContextHolder

/**
 * 系统配置初始化启动器
 * @author heng
 * @since 2022/4/10
 */
@Order(1)
@Component
class SysConfigInitLauncher: ApplicationRunner {

    @Lazy
    @Resource
    private lateinit var sysConfigService: SysConfigService

    override fun run(args: ApplicationArguments?) {

        sysConfigService.list().forEach {
            SysConfigContextHolder.setConfig(it.configKey!!, it.configValue)
        }

    }
}