package top.shuzz.epub.library.modular.dto

/**
 * 文件后端处理数据对象
 * @author heng
 * @since 2022/4/12
 */
data class FileBackendHandleDto(
    var accountId: String? = null,
    var fileList: MutableList<BookFileNamesDto>? = null
)
