package top.shuzz.epub.library.modular.service

import cn.hutool.core.io.FileUtil
import cn.hutool.core.util.StrUtil
import nl.siegmann.epublib.domain.Author
import nl.siegmann.epublib.domain.Book
import nl.siegmann.epublib.epub.EpubReader
import org.springframework.stereotype.Service
import top.shuzz.epub.library.commons.exception.ServiceException
import top.shuzz.epub.library.commons.response.enums.ErrorEnum
import top.shuzz.epub.library.commons.util.SysConfigContextHolder
import top.shuzz.epub.library.modular.dto.EpubMetaInfoDto
import java.io.FileInputStream

/**
 * @author heng
 * @since 2022/4/11
 */
@Service
class EpubFileService {

    /**
     * 解析 ePub 文件
     * @param accountId 用户账号ID
     * @param epubFile 目标文件
     */
    fun parseEpubFile(accountId: String?, epubFile: String?): EpubMetaInfoDto {
        epubFile ?: throw ServiceException(ErrorEnum.PARAMS_INVALID, "ePub File Path Cannot be Empty.")
        accountId ?: throw ServiceException(ErrorEnum.PARAMS_INVALID, "accountId Cannot be Empty.")

        // todo 通过数据库检查用户账号ID

        val filePath = SysConfigContextHolder.getUserDataRootDir() + "/" + accountId + "/" + epubFile
        if (!FileUtil.exist(filePath)) throw ServiceException(ErrorEnum.PARAMS_INVALID, "Cannot Find File: $epubFile")

        val epubBook = this.loadEpubFile(filePath)

        // 书目标题
        val bookTitle = epubBook.title ?: "Untitled"
        // 书目作者
        val authors = this.parseAuthors(epubBook.metadata.authors)
        // 书目简介
        val description = this.parseDescription(epubBook.metadata.descriptions)
        // 书目封面资源URL
        val coverUrl = epubBook.coverImage?.href
        // 书目OPF文件URL
        val opfUrl = epubBook.opfResource?.href
        // 书目UUID
        var epubUid: String? = null
        val epubIds = epubBook.metadata.identifiers.filter { it.scheme == "UUID" }
        if (epubIds.isNotEmpty()) epubUid = epubIds[0].value

        return EpubMetaInfoDto(bookTitle, authors, description, epubUid, coverUrl, opfUrl)
    }

    /**
     * 加载 ePub 文件
     */
    private fun loadEpubFile(epubFile: String): Book {
        val reader = EpubReader()
        return reader.readEpub(FileInputStream(epubFile))
    }

    /**
     * 处理作者列表
     */
    private fun parseAuthors(authors: List<Author>?): String {
        if (authors == null || authors.isEmpty()) {
            return "Unknown Author"
        }

        val builder = StrUtil.builder()
        for (i in authors.indices) {
            val firstName = authors[i].firstname
            val lastName = authors[i].lastname
            builder.append(lastName)
            if (!StrUtil.isEmptyIfStr(firstName)) {
                builder.append(" ").append(firstName)
            }
            if (i != authors.size - 1) {
                builder.append(",")
            }
        }

        return builder.toString()
    }

    /**
     * 处理简介
     */
    private fun parseDescription(descriptions: List<String>?): String {
        if (descriptions == null || descriptions.isEmpty()) return ""

        val builder = StrUtil.builder()
        descriptions.forEach { builder.append(it) }

        return builder.toString()
    }
}