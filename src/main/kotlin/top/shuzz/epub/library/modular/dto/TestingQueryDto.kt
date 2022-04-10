package top.shuzz.epub.library.modular.dto

import top.shuzz.epub.library.commons.request.PageQueryDto

/**
 * @author heng
 * @since 2022/4/10
 */
data class TestingQueryDto(
    var username: String? = null
): PageQueryDto()
