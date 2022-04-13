package top.shuzz.epub.library.commons.util

import com.baomidou.mybatisplus.extension.plugins.pagination.Page

/**
 * MybatisPlus 工具类
 * @author heng
 * @since 2022/4/13
 */
class MybatisPlusUtil {

    companion object {
        /**
         * 获取分页参数对象
         */
        fun <T> queryPageParam(curPage: Long?, sizePerPage: Long?): Page<T> {
            val cur = curPage ?: 1L
            val size = sizePerPage ?: 10L
            return Page<T>(cur, size)
        }
    }
}