package top.shuzz.epub.library.modular.mapper

import com.baomidou.mybatisplus.core.metadata.IPage
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import top.shuzz.epub.library.modular.vo.BookBaseInfoVo

/**
 * @author heng
 * @since 2022/4/13
 */
@Mapper
interface BookInfoQueryMapper {

    /**
     * 查询书目基本信息
     * @param accountId 用户账号ID
     * @param bookTitleLike 书目标题
     * @param bookAuthorsLike 书目作者
     */
    fun queryBookBaseInfo(page: Page<BookBaseInfoVo>,
                          @Param("accountId") accountId: Long?,
                          @Param("bookTitle") bookTitleLike: String?,
                          @Param("bookAuthors") bookAuthorsLike: String?): IPage<BookBaseInfoVo>
}