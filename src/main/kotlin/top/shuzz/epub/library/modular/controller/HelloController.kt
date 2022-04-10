package top.shuzz.epub.library.modular.controller

import cn.hutool.log.LogFactory
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.shuzz.epub.library.commons.exception.ServiceException
import top.shuzz.epub.library.commons.response.enums.ErrorEnum
import top.shuzz.epub.library.commons.response.ResponseData
import top.shuzz.epub.library.modular.dto.TestingQueryDto

/**
 * @author heng
 * @since 2022/4/9
 */
@RestController
@RequestMapping(value = ["/hello"])
class HelloController {

    private val log = LogFactory.get()

    @GetMapping(value = ["ok"])
    fun ok(): ResponseData {
        return ResponseData.success()
    }

    @GetMapping(value = ["failed"])
    fun failed(): ResponseData {
        return ResponseData.failed(ErrorEnum.PARAMS_INVALID, null)
    }

    @GetMapping(value = ["error"])
    fun error(): ResponseData {
        return ResponseData.failed(ErrorEnum.SYS_ERROR, "Something Error")
    }

    @GetMapping(value = ["result"])
    fun result(): ResponseData {
        return ResponseData.success("data_data_data")
    }

    @PostMapping(value = ["testing-page-query"])
    fun testingPageQuery(@RequestBody queryDto: TestingQueryDto): ResponseData {
        val result = JsonObject()
        result.addProperty("curPage", queryDto.getCurPage())
        result.addProperty("size", queryDto.getSize())
        result.addProperty("username", queryDto.username)

        log.info("Fetched PageQueryParams: {}", result.toString())

        return ResponseData.success()
    }

    @GetMapping(value = ["testing-exception"])
    fun testingException(): ResponseData {
        throw ServiceException(ErrorEnum.UNSUPPORTED_FILES_ERROR)
    }
}