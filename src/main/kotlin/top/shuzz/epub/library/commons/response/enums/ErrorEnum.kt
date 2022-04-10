package top.shuzz.epub.library.commons.response.enums

/**
 * 错误信息枚举
 * @author heng
 * @since 2022/4/9
 */
enum class ErrorEnum(code: Int, msg: String): BaseResponseEnum {

    /**
     * 参数错误
     */
    PARAMS_INVALID(400, "PARAMS_INVALID"),

    /**
     * 不受支持文件类型错误
     */
    UNSUPPORTED_FILES_ERROR(400, "UNSUPPORTED_FILES_ERROR"),

    /**
     * 用户名或密码错误
     */
    USERNAME_OR_PASSWORD_INVALID(401, "USERNAME_OR_PASSWORD_INVALID"),

    /**
     * 系统错误
     */
    SYS_ERROR(500, "SYS_ERROR")
    ;

    private val code: Int
    private val msg: String

    init {
        this.code = code
        this.msg = msg
    }

    override fun code(): Int {
        return this.code
    }

    override fun msg(): String {
        return this.msg
    }
}