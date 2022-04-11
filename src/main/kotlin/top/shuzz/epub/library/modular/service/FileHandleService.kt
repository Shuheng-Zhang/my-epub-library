package top.shuzz.epub.library.modular.service

import cn.hutool.core.util.IdUtil
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import top.shuzz.epub.library.commons.exception.ServiceException
import top.shuzz.epub.library.commons.response.enums.ErrorEnum
import top.shuzz.epub.library.commons.util.SysConfigContextHolder
import java.io.File

/**
 * @author heng
 * @since 2022/4/10
 */
@Service
class FileHandleService {

    companion object {
        private const val EPUB_EXT = ".epub"
        private const val EPUB_TYPE = "application/epub"
        private const val EPUB_ZIP_TYPE = "application/epub+zip"
    }


    /**
     * 存储文件到上传目录
     */
    fun moveFilesToUploadedDir(files: List<MultipartFile>): List<String> {

        if (!this.checkFileType(files)) {
            throw ServiceException(ErrorEnum.UNSUPPORTED_FILES_ERROR, "Type (*.epub) Supported Only.")
        }

        val fileStorageDir = "${SysConfigContextHolder.getConfig(SysConfigContextHolder.ROOT_PATH)}${
            SysConfigContextHolder.getConfig(SysConfigContextHolder.UPLOADED_FILE_DIR)
        }"

        val storedFileList = mutableListOf<String>()
        files.forEach {
            try {
                val destFileName = IdUtil.getSnowflakeNextIdStr() + EPUB_EXT
                val destFilePath = "$fileStorageDir/$destFileName"
                val destFile = File(destFilePath)

                it.transferTo(destFile)

                storedFileList.add(destFileName)
            } catch (e : Exception) {
                throw ServiceException(ErrorEnum.SYS_ERROR, e.message ?: e.javaClass.name)
            }
        }

        return storedFileList
    }

    /**
     * 检查文件类型(*.epub)
     * @return true-检查通过, false-部分文件类型不正确
     */
    fun checkFileType(files: List<MultipartFile>): Boolean {
        files.forEach {
            if (it.contentType != EPUB_TYPE && it.contentType != EPUB_ZIP_TYPE) {
                return false
            }
        }
        return true
    }
}