package top.shuzz.epub.library.modular.dto

/**
 * 系统配置项查询对象
 * @author heng
 * @since 2022/4/10
 */
data class SysConfigQueryDto(

    /**
     * 配置项键名
     */
    var configKey: String? = null,

    /**
     * 配置项名称
     */
    var configName: String? = null
)
