package top.shuzz.epub.library.modular.vo

/**
 * 上传文件信息对象
 * @author heng
 * @since 2022/4/12
 */
data class UploadedFileInfoVo(
    /**
     * 源文件名
     */
    var originalFileName: String? = null,
    /**
     * 转入上传存储目录后的文件名
     */
    var storedFileName: String? = null,
    /**
     * 存储文件容量
     */
    var storedFileSize: Long? = null
)
