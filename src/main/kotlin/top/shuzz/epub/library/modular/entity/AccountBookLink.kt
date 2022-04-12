package top.shuzz.epub.library.modular.entity

import com.baomidou.mybatisplus.annotation.*
import java.time.LocalDateTime

/**
 * @author heng
 * @since 2022/4/12
 */
@TableName(value = "account_book_link")
data class AccountBookLink(
    @TableId(type = IdType.ASSIGN_ID)
    var id: Long? = null,
    @TableField(value = "account_id")
    var accountId: Long? = null,
    @TableField(value = "book_file_id")
    var bookFileId: Long? = null,
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    var createdTime: LocalDateTime? = null,
    @TableField(value = "deleted")
    @TableLogic
    var deleted: Int? = null
)
