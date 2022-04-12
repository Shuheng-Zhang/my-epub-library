package top.shuzz.epub.library.modular.service

import cn.hutool.core.io.FileUtil
import cn.hutool.core.util.IdUtil
import cn.hutool.core.util.StrUtil
import cn.hutool.log.LogFactory
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import top.shuzz.epub.library.commons.exception.ServiceException
import top.shuzz.epub.library.commons.response.enums.ErrorEnum
import top.shuzz.epub.library.commons.util.SysConfigContextHolder
import top.shuzz.epub.library.modular.dto.AccountFilesMovementDto
import top.shuzz.epub.library.modular.dto.FileBackendHandleDto
import top.shuzz.epub.library.modular.vo.UploadedFileInfoVo
import java.io.File

/**
 * @author heng
 * @since 2022/4/10
 */
@Service
class FileHandleService {

    private val log = LogFactory.get()

    companion object {
        private const val EPUB_EXT = ".epub"
        private const val EPUB_TYPE = "application/epub"
        private const val EPUB_ZIP_TYPE = "application/epub+zip"
    }


    /**
     * 存储文件到上传目录
     */
    fun moveFilesToUploadedDir(files: List<MultipartFile>): List<UploadedFileInfoVo> {

        if (!this.checkFileType(files)) {
            throw ServiceException(ErrorEnum.UNSUPPORTED_FILES_ERROR, "Type (*.epub) Supported Only.")
        }

        val fileStorageDir = "${SysConfigContextHolder.getConfig(SysConfigContextHolder.ROOT_PATH)}${
            SysConfigContextHolder.getConfig(SysConfigContextHolder.UPLOADED_FILE_DIR)
        }"

        val storedFileList = mutableListOf<UploadedFileInfoVo>()
        files.forEach {
            try {
                val destFileName = IdUtil.getSnowflakeNextIdStr() + EPUB_EXT
                val destFilePath = "$fileStorageDir/$destFileName"
                val destFile = File(destFilePath)

                it.transferTo(destFile)

                storedFileList.add(UploadedFileInfoVo(it.originalFilename, destFileName, it.size))
            } catch (e : Exception) {
                throw ServiceException(ErrorEnum.SYS_ERROR, e.message ?: e.javaClass.name)
            }
        }

        return storedFileList
    }

    /**
     * 将文件移动到用户账号数据目录
     * @param accountId 用户账户ID
     * @param fileList 目标文件列表
     */
    fun moveFilesToAccountDataDir(accountId: String?, fileList: List<String>?): List<String> {
        if (StrUtil.isEmptyIfStr(accountId)) throw ServiceException(ErrorEnum.PARAMS_INVALID, "accountId Cannot be Empty.")

        fileList ?: return listOf()

        // 检查或创建用户数据目录
        val accountBookDir = this.checkAccountBookDir(accountId!!)

        // 将文件移动到用户数据目录
        val completedList = mutableListOf<String>()
        this.checkTargetFilesExisted(fileList).forEach {
            val srcFile = File(it)
            val destFile = File(accountBookDir + "/" + srcFile.name)
            FileUtil.move(srcFile, destFile, true)

            completedList.add(srcFile.name)
        }

        return completedList
    }

    /**
     * 获取文件移动列表
     */
    fun getFileMovementList(backendHandleDto: FileBackendHandleDto): AccountFilesMovementDto {
        val movementList = mutableListOf<String>()
        backendHandleDto.fileList?.forEach { namesDto ->
            namesDto.storedFileName?.let { movementList.add(it) }
        }
        return AccountFilesMovementDto(backendHandleDto.accountId, movementList)
    }

    /**
     * 检查文件类型(*.epub)
     * @return true-检查通过, false-部分文件类型不正确
     */
    private fun checkFileType(files: List<MultipartFile>): Boolean {
        files.forEach {
            if (it.contentType != EPUB_TYPE && it.contentType != EPUB_ZIP_TYPE) {
                return false
            }
        }
        return true
    }

    /**
     * 检查并创建用户账户书目存储目录
     */
    private fun checkAccountBookDir(accountId: String): String {
        val dirPath = SysConfigContextHolder.getBookFileDir(accountId)
        if (!FileUtil.exist(dirPath) && !FileUtil.isDirectory(dirPath)) {
            FileUtil.mkdir(dirPath)
            log.info("Created Account Book Directory :$accountId${SysConfigContextHolder.getConfig(SysConfigContextHolder.BOOK_FILE_DIR)}")
        }

        return dirPath
    }

    /**
     * 检查目标文件是否存在
     * @return 可操作文件列表
     */
    private fun checkTargetFilesExisted(fileList: List<String>): List<String> {

        val fileDir = SysConfigContextHolder.getUploadedFileDir()

        val resultList = mutableListOf<String>()
        val errorList = mutableListOf<String>()
        fileList.forEach {
            val filePath = "$fileDir/$it"
            if (FileUtil.exist(filePath)) {
                resultList.add(filePath)
            } else {
                errorList.add(it)
            }
        }

        if (errorList.size > 0) {
            log.warn("Cannot Find File(s) for Processing Movement: ${errorList.size}")
            errorList.forEach { log.warn("==> $it") }
        }

        return resultList

    }
}