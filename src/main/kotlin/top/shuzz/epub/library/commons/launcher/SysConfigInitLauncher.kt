package top.shuzz.epub.library.commons.launcher

import cn.hutool.core.io.FileUtil
import cn.hutool.core.util.StrUtil
import cn.hutool.log.LogFactory
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import top.shuzz.epub.library.modular.service.SysConfigService
import javax.annotation.Resource
import org.springframework.context.annotation.Lazy
import org.springframework.core.annotation.Order
import top.shuzz.epub.library.commons.util.SysConfigContextHolder
import java.io.File

/**
 * 系统配置初始化启动器
 * @author heng
 * @since 2022/4/10
 */
@Order(1)
@Component
class SysConfigInitLauncher: ApplicationRunner {

    private val log = LogFactory.get()

    @Lazy
    @Resource
    private lateinit var sysConfigService: SysConfigService

    override fun run(args: ApplicationArguments?) {

        this.loadSysConfigs()

        this.checkAndCreateDirs()
    }

    /**
     * 加载系统配置
     */
    private fun loadSysConfigs() {
        sysConfigService.list().forEach { config ->
            when(val key = config.configKey) {
                SysConfigContextHolder.ROOT_PATH -> {
                    val value = config.configValue ?: System.getProperty("user.dir")
                    SysConfigContextHolder.setConfig(key, value, StrUtil.isEmptyIfStr(config.configValue))
                }
                SysConfigContextHolder.USER_DATA_ROOT_DIR -> {
                    val value = config.configValue ?: SysConfigContextHolder.USER_DATA_ROOT_DIR_DEFAULT
                    SysConfigContextHolder.setConfig(key, value, StrUtil.isEmptyIfStr(config.configValue))
                }
                SysConfigContextHolder.UPLOADED_FILE_DIR -> {
                    val value = config.configValue ?: SysConfigContextHolder.UPLOADED_FILE_DIR_DEFAULT
                    SysConfigContextHolder.setConfig(key, value, StrUtil.isEmptyIfStr(config.configValue))
                }
                else -> {
                    SysConfigContextHolder.setConfig(key!!, config.configValue)
                }
            }
        }
    }

    /**
     * 检查并创建数据目录
     */
    private fun checkAndCreateDirs() {
        val uploadedFileDir = File(SysConfigContextHolder.getUploadedFileDir())
        val userDataRootDir = File(SysConfigContextHolder.getUserDataRootDir())
        if (!FileUtil.exist(uploadedFileDir) || !FileUtil.isDirectory(uploadedFileDir)) {
            FileUtil.mkdir(uploadedFileDir)
            log.info("Created Dir [${SysConfigContextHolder.UPLOADED_FILE_DIR}]")
        }
        if (!FileUtil.exist(userDataRootDir) || !FileUtil.isDirectory(userDataRootDir)) {
            FileUtil.mkdir(userDataRootDir)
            log.info("Created Dir [${SysConfigContextHolder.USER_DATA_ROOT_DIR}]")
        }
    }

}