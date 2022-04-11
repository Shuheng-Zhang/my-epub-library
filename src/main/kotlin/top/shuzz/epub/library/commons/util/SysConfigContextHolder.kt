package top.shuzz.epub.library.commons.util

import cn.hutool.core.util.StrUtil
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
        private const val USER_DATA_ROOT_DIR_DEFAULT = "/data"

        /**
         * 默认值-文件上传存储根目录
         */
        private const val UPLOADED_FILE_DIR_DEFAULT = "/uploaded"

        /**
         * 设置配置参数到系统运行时
         * @param key 配置项键名
         * @param value 配置项值
         */
        fun setConfig(key: String, value: String?) {
            value?.let {
                log.info("Loaded SysConfig [$key: $value]")
                SYS_CONFIG_MAP[key] = value
            }
        }

        /**
         * 获取文件上传存储目录路径
         */
        fun getUploadedFileDir(): String {
            val root = this.getRootPath()
            var uploadedFileDir = this.getConfig(UPLOADED_FILE_DIR)
            if (StrUtil.isEmptyIfStr(uploadedFileDir)) {
                uploadedFileDir = UPLOADED_FILE_DIR_DEFAULT

                SYS_CONFIG_MAP[UPLOADED_FILE_DIR] = uploadedFileDir
                log.warn("Failed to Fetch Value of Config [$UPLOADED_FILE_DIR], Use Default: $uploadedFileDir")
            }

            return root + uploadedFileDir
        }

        /**
         * 获取用户数据根目录
         */
        fun getUserDataRootDir(): String {
            val root = this.getRootPath()
            var userDataDir = this.getConfig(USER_DATA_ROOT_DIR)
            if (StrUtil.isEmptyIfStr(userDataDir)) {
                userDataDir = USER_DATA_ROOT_DIR_DEFAULT

                SYS_CONFIG_MAP[USER_DATA_ROOT_DIR] = userDataDir
                log.warn("Failed to Fetch Value of Config [$USER_DATA_ROOT_DIR], Use Default: $userDataDir")
            }

            return root + userDataDir

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

        /**
         * 获取系统根路径
         */
        private fun getRootPath(): String {
            var root = this.getConfig(ROOT_PATH)
            if (StrUtil.isEmptyIfStr(root)) {
                root = System.getProperty("user.dir")

                SYS_CONFIG_MAP[ROOT_PATH] = root
                log.warn("Failed to Fetch Value of Config [$ROOT_PATH], Use Default: $root")
            }

            return root!!
        }

    }
}