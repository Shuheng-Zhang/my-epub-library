package top.shuzz.epub.library.modular.service

import org.springframework.stereotype.Service
import top.shuzz.epub.library.modular.mapper.BookInfoQueryMapper
import javax.annotation.Resource
import org.springframework.context.annotation.Lazy
import top.shuzz.epub.library.commons.response.PageResultVo
import top.shuzz.epub.library.commons.util.MybatisPlusUtil
import top.shuzz.epub.library.modular.dto.BookInfoQueryDto
import top.shuzz.epub.library.modular.vo.BookBaseInfoVo
import top.shuzz.epub.library.modular.vo.BookDetailInfoVo

/**
 * @author heng
 * @since 2022/4/13
 */
@Service
class BookInfoQueryService {

    @Lazy
    @Resource
    private lateinit var bookInfoQueryMapper: BookInfoQueryMapper


    /**
     * 分页查询书目基本信息
     */
    fun fetchBookBaseInfoByPage(queryDto: BookInfoQueryDto): PageResultVo<BookBaseInfoVo> {
        val pageParam = MybatisPlusUtil.queryPageParam<BookBaseInfoVo>(queryDto.getCurPage(), queryDto.getSize())

        val pageResult = PageResultVo<BookBaseInfoVo>()
        this.bookInfoQueryMapper.queryBookBaseInfo(pageParam, queryDto.accountId?.toLong(),
            queryDto.bookTitle,
            queryDto.bookAuthors).let {
            pageResult.records = it.records
            pageResult.curPage = it.current
            pageResult.pages = it.pages
            pageResult.total = it.total
        }

        return pageResult
    }

    /**
     * 查询书目详情
     */
    fun fetchBookDetailInfo(linkId: String?): BookDetailInfoVo? {
        return this.bookInfoQueryMapper.queryBookDetailInfo(linkId?.toLong())
    }
}