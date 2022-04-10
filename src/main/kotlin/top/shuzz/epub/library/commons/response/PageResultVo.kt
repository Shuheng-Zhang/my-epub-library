package top.shuzz.epub.library.commons.response

/**
 * 分页数据输出视图
 * @author heng
 * @since 2022/4/10
 */
data class PageResultVo<T>(
    /**
     * 数据记录
     */
    var records: MutableList<T>? = null,
    /**
     * 当前页
     */
    var curPage: Long? = null,
    /**
     * 页总数
     */
    var pages: Long? = null,
    /**
     * 数据总数
     */
    var total: Long? = null,
)
