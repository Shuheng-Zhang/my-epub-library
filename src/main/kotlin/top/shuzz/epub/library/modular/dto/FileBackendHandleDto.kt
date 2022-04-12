package top.shuzz.epub.library.modular.dto

/**
 * 文件后端处理数据对象
 * @author heng
 * @since 2022/4/12
 */
data class FileBackendHandleDto(
    /**
     * 用户账号ID
     */
    var accountId: String? = null,
    /**
     * 待处理文件列表
     */
    var fileList: MutableList<BookFileNamesDto>? = null
)
