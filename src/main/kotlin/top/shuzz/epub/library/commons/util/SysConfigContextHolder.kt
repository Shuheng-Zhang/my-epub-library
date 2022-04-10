package top.shuzz.epub.library.commons.util

import java.util.concurrent.ConcurrentHashMap

/**
 * 全局系统配置处理器
 * @author heng
 * @since 2022/4/10
 */
class SysConfigContextHolder {

    companion object {

        private val SYS_CONFIG_MAP = ConcurrentHashMap<String, String?>()

        /**
         * 配置项-文件上传存储根目录
         */
        val UPLOADED_FILE_DIR = "UPLOADED_FILE_DIR"

        /**
         * 配置项-用户数据根目录
         */
        val USER_DATA_ROOT_DIR = "USER_DATA_ROOT_DIR"

        /**
         * 配置项-系统数据根路径
         */
        val ROOT_PATH = "ROOT_PATH"

        /**
         * 设置配置参数到系统运行时
         * @param key 配置项键名
         * @param value 配置项值
         */
        fun setConfig(key: String, value: String?) {
            SYS_CONFIG_MAP[key] = value
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
            return SYS_CONFIG_MAP[key]?.toBoolean()
        }

        /**
         * 获取配置数值
         * @param key 配置项键名
         * @return 整数类型值
         */
        fun getConfigInteger(key: String): Int? {
            return SYS_CONFIG_MAP[key]?.toInt()
        }

        /**
         * 获取配置数值
         * @param key 配置项键名
         * @return 长整型类型值
         */
        fun getConfigLong(key: String): Long? {
            return SYS_CONFIG_MAP[key]?.toLong()
        }
    }
}