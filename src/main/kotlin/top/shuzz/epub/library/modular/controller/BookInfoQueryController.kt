package top.shuzz.epub.library.modular.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.shuzz.epub.library.modular.service.BookInfoQueryService
import javax.annotation.Resource
import org.springframework.context.annotation.Lazy
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import top.shuzz.epub.library.commons.response.ResponseData
import top.shuzz.epub.library.modular.dto.BookInfoQueryDto

/**
 * @author heng
 * @since 2022/4/13
 */
@RestController
@RequestMapping(value = ["/book-info-query"])
class BookInfoQueryController {

    @Lazy
    @Resource
    private lateinit var bookInfoQueryService: BookInfoQueryService

    @PostMapping(value = ["base"])
    fun getBookBaseInfoByPage(@RequestBody queryDto: BookInfoQueryDto): ResponseData {
        val result = this.bookInfoQueryService.fetchBookBaseInfoByPage(queryDto)
        return ResponseData.success(result)
    }
}