package top.shuzz.epub.library.modular.dto

/**
 * 书目名称数据对象
 * @author heng
 * @since 2022/4/12
 */
data class BookFileNamesDto(
    var originalFileName: String? = null,
    var storedFileName: String? = null
)