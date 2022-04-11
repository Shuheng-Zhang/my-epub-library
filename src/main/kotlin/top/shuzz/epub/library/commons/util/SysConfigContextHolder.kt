package top.shuzz.epub.library.commons.util

import cn.hutool.log.LogFactory
import java.util.concurrent.ConcurrentHashMap

/**
 * 全局系统配置处理器
 * @author heng
 * @since 2022/4/10
 */
class SysConfigContextHolder {

    companion object {

        private val log = LogFactory.get()

        private val SYS_CONFIG_MAP = ConcurrentHashMap<String, String?>()

        /**
         * 配置项-文件上传存储根目录
         */
        const val UPLOADED_FILE_DIR = "UPLOADED_FILE_DIR"

        /**
         * 配置项-用户数据根目录
         */
        const val USER_DATA_ROOT_DIR = "USER_DATA_ROOT_DIR"

        /**
         * 配置项-系统数据根路径
         */
        const val ROOT_PATH = "ROOT_PATH"

        /**
         * 默认值-用户数据根目录
         */
        const val USER_DATA_ROOT_DIR_DEFAULT = "/data"

        /**
         * 默认值-文件上传存储根目录
         */
        const val UPLOADED_FILE_DIR_DEFAULT = "/uploaded"

        /**
         * 设置配置参数到系统运行时
         * @param key 配置项键名
         * @param value 配置项值
         */
        fun setConfig(key: String, value: String?, usingDefault: Boolean = false) {
            if (usingDefault) {
                log.warn("Loaded SysConfig [$key: $value] (Using Default Value)")
            } else {
                log.info("Loaded SysConfig [$key: $value]")
            }
            SYS_CONFIG_MAP[key] = value
        }

        /**
         * 获取文件上传存储目录路径
         */
        fun getUploadedFileDir(): String {
            return this.getConfig(ROOT_PATH) + this.getConfig(UPLOADED_FILE_DIR)
        }

        /**
         * 获取用户数据根目录
         */
        fun getUserDataRootDir(): String {
            return this.getConfig(ROOT_PATH) + this.getConfig(USER_DATA_ROOT_DIR)
        }

        /**
         * 获取配置数值
         * @param key 配置项键名
         * @return 字符串类型值(默认)
         */
        fun getConfig(key: String): String? {
            return SYS_CONFIG_MAP[key]
        }

        /**
         * 获取配置数值
         * @param key 配置项键名
         * @return 布尔类型值
         */
        fun getConfigBoolean(key: String): Boolean? {
            return this.getConfig(key)?.toBoolean()
        }

        /**
         * 获取配置数值
         * @param key 配置项键名
         * @return 整数类型值
         */
        fun getConfigInteger(key: String): Int? {
            return this.getConfig(key)?.toInt()
        }

        /**
         * 获取配置数值
         * @param key 配置项键名
         * @return 长整型类型值
         */
        fun getConfigLong(key: String): Long? {
            return this.getConfig(key)?.toLong()
        }
    }
}