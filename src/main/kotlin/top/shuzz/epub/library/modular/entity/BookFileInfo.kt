package top.shuzz.epub.library.modular.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName

/**
 * @author heng
 * @since 2022/4/12
 */
@TableName(value = "book_file_info")
data class BookFileInfo(
    @TableId(type = IdType.ASSIGN_ID)
    var id: Long? = null,
    @TableField(value = "book_file_name")
    val bookFileName: String? = null,
    @TableField(value = "book_file_original_name")
    var bookFileOriginalName: String? = null,
    @TableField(value = "book_cover_url")
    var bookCoverUrl: String? = null,
    @TableField(value = "book_opf_url")
    var bookOpfUrl: String? = null,
    @TableField(value = "book_unpacked_url")
    var bookUnpackedUrl: String? = null,
    @TableField(value = "book_cover_outside_url")
    var bookCoverOutsideUrl: String? = null,
    @TableField(value = "book_file_size")
    var bookFileSize: Long? = null,
    @TableField(value = "book_unpacked_size")
    var bookUnpackedSize: Long? = null
)
