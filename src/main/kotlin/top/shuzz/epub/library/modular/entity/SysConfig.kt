package top.shuzz.epub.library.modular.entity

import com.baomidou.mybatisplus.annotation.*
import java.time.LocalDateTime

/**
 * 系统配置项
 * @author heng
 * @since 2022/4/10
 */
@TableName(value = "sys_config")
data class SysConfig(
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID) var id: Long? = null,
    /**
     * 配置项键名
     */
    @TableField(value = "config_key") var configKey: String? = null,
    /**
     * 配置项名称
     */
    @TableField(value = "config_name") var configName: String? = null,
    /**
     * 配置项值
     */
    @TableField(value = "config_value") var configValue: String? = null,
    /**
     * 配置项描述
     */
    @TableField(value = "config_description") var configDescription: String? = null,
    /**
     * 创建时间
     */
    @TableField(value = "created_time", fill = FieldFill.INSERT) var createdTime: LocalDateTime? = null,
    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE) var updateTime: LocalDateTime? = null
)
