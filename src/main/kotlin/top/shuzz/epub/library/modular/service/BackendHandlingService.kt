package top.shuzz.epub.library.modular.service

import cn.hutool.core.io.FileUtil
import cn.hutool.core.util.IdUtil
import cn.hutool.core.util.StrUtil
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import top.shuzz.epub.library.modular.dto.FileBackendHandleDto
import javax.annotation.Resource
import org.springframework.context.annotation.Lazy
import top.shuzz.epub.library.commons.util.SysConfigContextHolder
import top.shuzz.epub.library.modular.dto.BookFileNamesDto
import top.shuzz.epub.library.modular.entity.AccountBookLink
import top.shuzz.epub.library.modular.entity.BookBaseInfo
import top.shuzz.epub.library.modular.entity.BookDescription
import top.shuzz.epub.library.modular.entity.BookFileInfo
import java.io.File

/**
 * 后端处理服务
 * @author heng
 * @since 2022/4/12
 */
@Service
class BackendHandlingService {

    @Lazy
    @Resource
    private lateinit var accountBookLinkService: AccountBookLinkService

    @Lazy
    @Resource
    private lateinit var fileHandleService: FileHandleService

    @Lazy
    @Resource
    private lateinit var epubFileService: EpubFileService

    @Lazy
    @Resource
    private lateinit var bookBaseInfoService: BookBaseInfoService

    @Lazy
    @Resource
    private lateinit var bookDescriptionService: BookDescriptionService

    @Lazy
    @Resource
    private lateinit var bookFileInfoService: BookFileInfoService

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

        // 从已移动文件中筛选可进一步文件列表
        val processList = mutableListOf<BookFileNamesDto>()
        backendHandleDto.fileList?.forEach {
            if (movedFileList.contains(it.storedFileName)) { processList.add(it) }
        }

        // 执行后续处理
        processList.forEach {
            // 获取文件容量
            val fileSize = this.getFileSize(backendHandleDto.accountId, it.storedFileName)

            // 执行解析
            val result = epubFileService.parseEpubFile(backendHandleDto.accountId, it)

            // 获取封面
            val cover = epubFileService.fetchEpubCover(backendHandleDto.accountId, it.storedFileName)

            // 存储书目文件信息
            val bookFileId = IdUtil.getSnowflakeNextId()
            val bookFileInfo = BookFileInfo(id = bookFileId,
                bookFileName = it.storedFileName, bookFileOriginalName = it.originalFileName,
                bookCoverUrl = result.coverUrl, bookOpfUrl = result.opfUrl, bookCoverOutsideUrl = cover,
                bookFileSize = fileSize)
            this.bookFileInfoService.save(bookFileInfo)

            // 存储书目基本信息
            val bookBaseInfo = BookBaseInfo(bookFileId = bookFileId, bookTitle = result.bookTitle, bookAuthors = result.bookAuthors, epubUid = result.epubUid)
            this.bookBaseInfoService.save(bookBaseInfo)

            // 存储书目简介
            if (StrUtil.isNotBlank(result.bookDescription)) this.bookDescriptionService.save(BookDescription(bookFileId = bookFileId, bookDescription = result.bookDescription))

            // 创建书目与用户账号关联
            val link = AccountBookLink(accountId = backendHandleDto.accountId?.toLong(), bookFileId = bookFileId)
            this.accountBookLinkService.save(link)
        }
    }

    /**
     * 获取文件容量
     */
    private fun getFileSize(accountId: String?, fileName: String?): Long {
        val filePath = SysConfigContextHolder.getUserDataRootDir() + "/" + accountId + "/" + fileName
        val file = File(filePath)
        return FileUtil.size(file)
    }
}