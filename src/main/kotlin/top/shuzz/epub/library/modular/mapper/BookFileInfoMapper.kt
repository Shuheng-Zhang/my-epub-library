package top.shuzz.epub.library.modular.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import top.shuzz.epub.library.modular.entity.BookFileInfo

/**
 * @author heng
 * @since 2022/4/12
 */
@Mapper
interface BookFileInfoMapper: BaseMapper<BookFileInfo> {
}