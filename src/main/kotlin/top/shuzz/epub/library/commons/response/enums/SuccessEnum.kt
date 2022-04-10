package top.shuzz.epub.library.commons.response.enums

/**
 * 请求成功信息枚举
 * @author heng
 * @since 2022/4/9
 */
enum class SuccessEnum(code: Int, msg: String): BaseResponseEnum {
    OK(200, "OK")
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