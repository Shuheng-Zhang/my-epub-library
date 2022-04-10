package top.shuzz.epub.library.modular.vo

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

/**
 * @author heng
 * @since 2022/4/10
 */
data class SysConfigVo(
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
    var configDescription: String? = null,
    /**
     * 创建时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss") var createdTime: LocalDateTime? = null,
    /**
     * 更新时间
     */
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss") var updateTime: LocalDateTime? = null

)
