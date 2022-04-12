package top.shuzz.epub.library.modular.service

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.shuzz.epub.library.modular.entity.AccountBookLink
import top.shuzz.epub.library.modular.mapper.AccountBookLinkMapper

/**
 * @author heng
 * @since 2022/4/12
 */
@Service
class AccountBookLinkService: ServiceImpl<AccountBookLinkMapper, AccountBookLink>() {
}