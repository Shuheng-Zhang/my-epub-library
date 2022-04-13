package top.shuzz.epub.library.modular.vo

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

/**
 * 书目基本信息返回对象
 * @author heng
 * @since 2022/4/13
 */
data class BookBaseInfoVo(
    var id: String? = null,
    var bookTitle: String? = null,
    var bookAuthors: String? = null,
    var bookCoverOutsideUrl: String? = null,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") var createdTime: LocalDateTime? = null
)
