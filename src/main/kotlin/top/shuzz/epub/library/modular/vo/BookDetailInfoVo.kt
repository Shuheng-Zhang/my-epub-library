package top.shuzz.epub.library.modular.vo

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

/**
 * @author heng
 * @since 2022/4/17
 */
data class BookDetailInfoVo (
    var id: String? = null,
    var bookTitle: String? = null,
    var bookAuthors: String? = null,
    var bookCoverOutsideUrl: String? = null,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") var createdTime: LocalDateTime? = null,
    var bookDescription: String? = null,
    var bookFileSize: Long? = null,
    var bookUnpackedSize: Long? = null

)
