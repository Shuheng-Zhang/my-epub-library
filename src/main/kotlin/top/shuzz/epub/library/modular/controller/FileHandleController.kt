package top.shuzz.epub.library.modular.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import top.shuzz.epub.library.commons.response.ResponseData
import top.shuzz.epub.library.modular.service.FileHandleService
import javax.annotation.Resource

/**
 * @author heng
 * @since 2022/4/10
 */
@RestController
@RequestMapping(value = ["/file-handler"])
class FileHandleController {

    @Resource
    private lateinit var fileHandleService: FileHandleService

    @PostMapping(value = ["upload"])
    fun uploadFiles(@RequestPart(value = "files", required = true) files: List<MultipartFile>): ResponseData {
        val stored = this.fileHandleService.moveFilesToUploadedDir(files)
        return ResponseData.success(stored)
    }
}