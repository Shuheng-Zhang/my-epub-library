package top.shuzz.epub.library.modular.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

/**
 * @author heng
 * @since 2022/4/12
 */
@TableName(value = "book_description")
data class BookDescription(
    @TableId(type = IdType.ASSIGN_ID)
    var id: Long? = null,
    @TableField(value = "book_file_id")
    var bookFileId: Long? = null,
    @TableField(value = "book_description")
    var bookDescription: String? = null
)
