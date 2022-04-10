package top.shuzz.epub.library.commons.response.enums

/**
 * @author heng
 * @since 2022/4/9
 */
interface BaseResponseEnum {

    fun code(): Int

    fun msg(): String
}