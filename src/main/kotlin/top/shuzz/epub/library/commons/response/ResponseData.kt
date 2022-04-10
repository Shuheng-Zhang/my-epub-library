package top.shuzz.epub.library.commons.response

import top.shuzz.epub.library.commons.response.enums.BaseResponseEnum
import top.shuzz.epub.library.commons.response.enums.ErrorEnum
import top.shuzz.epub.library.commons.response.enums.SuccessEnum

/**
 * 响应数据
 * @author heng
 * @since 2022/4/9
 */
data class ResponseData(val code: Int, val msg: String, val data: Any?) {

    constructor(responseEnum: BaseResponseEnum, data: Any?): this(responseEnum.code(), responseEnum.msg(), data)

    companion object {

        fun success(): ResponseData {
            return ResponseData(SuccessEnum.OK, null)
        }
        fun success(data: Any?): ResponseData {
            return ResponseData(SuccessEnum.OK, data)
        }
        fun failed(errorEnum: ErrorEnum, errMsg: Any?): ResponseData {
            return ResponseData(errorEnum, errMsg)
        }
        fun failed(code: Int, msg: String, errInfo: String?): ResponseData {
            return ResponseData(code, msg, errInfo)
        }
    }
}