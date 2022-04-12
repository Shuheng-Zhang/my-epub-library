package top.shuzz.epub.library.modular.dto

/**
 * 上传文件信息对象
 * @author heng
 * @since 2022/4/12
 */
data class UploadedFileInfoDto(
    /**
     * 源文件名
     */
    var originalFileName: String? = null,
    /**
     * 转入上传存储目录后的文件名
     */
    var storedFileName: String? = null
)
