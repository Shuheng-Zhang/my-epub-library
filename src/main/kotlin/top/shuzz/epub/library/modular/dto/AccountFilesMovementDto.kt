package top.shuzz.epub.library.modular.dto

/**
 * 用户账号文件移动列表对象
 * @author heng
 * @since 2022/4/11
 */
data class AccountFilesMovementDto(
    /**
     * 用户账号ID
     */
    var accountId: String? = null,
    /**
     * 目标文件列表
     */
    var fileList: MutableList<String>? = null
)
