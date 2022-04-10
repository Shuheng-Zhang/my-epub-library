package top.shuzz.epub.library.commons.request

/**
 * 分页查询条件对象
 * @author heng
 * @since 2022/4/10
 */
open class PageQueryDto(curPage: Long = 1, size: Long = 10) {

    private val curPage: Long
    private val size: Long

    init {
        this.curPage = curPage
        this.size = size
    }

    fun getCurPage(): Long {
        return this.curPage
    }

    fun getSize(): Long {
        return this.size
    }
}