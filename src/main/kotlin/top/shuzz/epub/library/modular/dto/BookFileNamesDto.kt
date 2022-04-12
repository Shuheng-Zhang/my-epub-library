package top.shuzz.epub.library.modular.dto

/**
 * 书目名称数据对象
 * @author heng
 * @since 2022/4/12
 */
data class BookFileNamesDto(
    /**
     * 源文件名
     */
    var originalFileName: String? = null,
    /**
     * 存储文件名
     */
    var storedFileName: String? = null
)