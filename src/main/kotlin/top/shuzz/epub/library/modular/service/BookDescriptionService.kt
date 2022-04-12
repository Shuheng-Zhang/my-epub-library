package top.shuzz.epub.library.modular.service

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.shuzz.epub.library.modular.entity.BookDescription
import top.shuzz.epub.library.modular.mapper.BookDescriptionMapper

/**
 * @author heng
 * @since 2022/4/12
 */
@Service
class BookDescriptionService: ServiceImpl<BookDescriptionMapper, BookDescription>() {
}