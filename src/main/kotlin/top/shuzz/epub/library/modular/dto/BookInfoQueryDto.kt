package top.shuzz.epub.library.modular.dto

import top.shuzz.epub.library.commons.request.PageQueryDto

/**
 * 书目基本信息查询对象
 * @author heng
 * @since 2022/4/13
 */
data class BookInfoQueryDto(
    /**
     * 用户账号ID
     */
    var accountId: String? = null,
    /**
     * 书目标题
     */
    var bookTitle: String? = null,
    /**
     * 书目作者
     */
    var bookAuthors: String? = null
): PageQueryDto()
