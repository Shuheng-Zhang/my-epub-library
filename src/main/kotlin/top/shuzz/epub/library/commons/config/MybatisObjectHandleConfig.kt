package top.shuzz.epub.library.commons.config

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
import org.apache.ibatis.reflection.MetaObject
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * MybatisPlus 自动填充/自动更新处理
 * @author heng
 * @since 2022/4/10
 */
@Component
class MybatisObjectHandleConfig: MetaObjectHandler {
    override fun insertFill(metaObject: MetaObject?) {
        this.strictInsertFill(metaObject, "createdTime", LocalDateTime::class.java, LocalDateTime.now())
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime::class.java, LocalDateTime.now())
    }

    override fun updateFill(metaObject: MetaObject?) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::class.java, LocalDateTime.now())

    }
}