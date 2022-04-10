package top.shuzz.epub.library.commons.exception

import cn.hutool.core.util.StrUtil
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import top.shuzz.epub.library.commons.response.ResponseData
import top.shuzz.epub.library.commons.response.enums.ErrorEnum

/**
 * 统一全局异常处理器
 * @author heng
 * @since 2022/4/10
 */
@RestControllerAdvice
class GlobalExceptionHandler {

    /**
     * 处理应用业务异常
     */
    @ExceptionHandler(ServiceException::class)
    fun serviceException(e : ServiceException): ResponseData {
        return ResponseData.failed(e.getCode(), e.getMsg(), e.getErrorInfo())
    }

    /**
     * 处理应用全局其他异常
     */
    @ExceptionHandler(Exception::class)
    fun exception(e: Exception): ResponseData {
        return ResponseData(ErrorEnum.SYS_ERROR, StrUtil.builder(e.javaClass.name, ": ", e.message))
    }
}