package top.shuzz.epub.library.commons.exception

import top.shuzz.epub.library.commons.response.enums.ErrorEnum

/**
 * 应用业务异常
 * @author heng
 * @since 2022/4/10
 */
class ServiceException(errorEnum: ErrorEnum, errorInfo: String? = null) : RuntimeException(errorInfo) {

    private val code: Int
    private val msg: String
    private val errorInfo: String?

    init {
        this.code = errorEnum.code()
        this.msg = errorEnum.msg()
        this.errorInfo = errorInfo
    }

    fun getCode(): Int {
        return this.code
    }

    fun getMsg(): String {
        return this.msg
    }

    fun getErrorInfo(): String? {
        return this.errorInfo
    }
}