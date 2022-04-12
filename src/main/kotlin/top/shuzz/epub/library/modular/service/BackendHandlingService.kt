package top.shuzz.epub.library.modular.service

import cn.hutool.log.LogFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import top.shuzz.epub.library.modular.dto.FileBackendHandleDto
import javax.annotation.Resource
import org.springframework.context.annotation.Lazy
import top.shuzz.epub.library.modular.dto.BookFileNamesDto

/**
 * 后端处理服务
 * @author heng
 * @since 2022/4/12
 */
@Service
class BackendHandlingService {

    private val log = LogFactory.get()

    @Lazy
    @Resource
    private lateinit var fileHandleService: FileHandleService

    @Lazy
    @Resource
    private lateinit var epubFileService: EpubFileService

    /**
     * 执行后端处理
     *
     * 处理用户文件转移
     *
     * 处理ePub元数据解析
     */
    @Async("async-task-executor")
    fun execFileBackendHandle(backendHandleDto: FileBackendHandleDto) {

        // 获取待移动文件列表
        val movementDto = fileHandleService.getFileMovementList(backendHandleDto)
        // 将文件移动到用户数据目录
        val movedFileList = fileHandleService.moveFilesToAccountDataDir(movementDto.accountId, movementDto.fileList)

        // 从已移动文件中筛选可解析文件列表
        val parsableList = mutableListOf<BookFileNamesDto>()
        backendHandleDto.fileList?.forEach {
            if (movedFileList.contains(it.storedFileName)) { parsableList.add(it) }
        }

        // 执行解析
        parsableList.forEach {
            val result = epubFileService.parseEpubFile(backendHandleDto.accountId, it)
            log.info("Parsed ePub: {}", result.toString())
            // todo: 存储解析结果
        }

    }

}