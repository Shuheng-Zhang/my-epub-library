package top.shuzz.epub.library.modular.dto

/**
 * @author heng
 * @since 2022/4/10
 */
data class SysConfigDto(
    /**
     * 主键
     */
    var id: String? = null,
    /**
     * 配置项键名
     */
    var configKey: String? = null,
    /**
     * 配置项名称
     */
    var configName: String? = null,
    /**
     * 配置项值
     */
    var configValue: String? = null,
    /**
     * 配置项描述
     */
    var configDescription: String? = null
)
