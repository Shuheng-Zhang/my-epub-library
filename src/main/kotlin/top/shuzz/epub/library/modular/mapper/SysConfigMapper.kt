package top.shuzz.epub.library.modular.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import top.shuzz.epub.library.modular.entity.SysConfig

/**
 * @author heng
 * @since 2022/4/10
 */
@Mapper
interface SysConfigMapper: BaseMapper<SysConfig> {
}