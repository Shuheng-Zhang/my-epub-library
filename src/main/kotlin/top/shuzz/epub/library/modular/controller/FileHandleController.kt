package top.shuzz.epub.library.modular.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import top.shuzz.epub.library.commons.response.ResponseData
import top.shuzz.epub.library.modular.dto.FileBackendHandleDto
import top.shuzz.epub.library.modular.service.FileHandleService
import javax.annotation.Resource
import org.springframework.context.annotation.Lazy
import org.springframework.web.bind.annotation.DeleteMapping
import top.shuzz.epub.library.modular.service.BackendHandlingService

/**
 * @author heng
 * @since 2022/4/10
 */
@RestController
@RequestMapping(value = ["/file-handler"])
class FileHandleController {

    @Lazy
    @Resource
    private lateinit var fileHandleService: FileHandleService

    @Lazy
    @Resource
    private lateinit var backendHandlingService: BackendHandlingService

    @PostMapping(value = ["upload"])
    fun uploadFiles(@RequestPart(value = "files", required = true) files: List<MultipartFile>): ResponseData {
        val stored = this.fileHandleService.moveFilesToUploadedDir(files)
        return ResponseData.success(stored)
    }

    @DeleteMapping(value = ["remove-uploaded"])
    fun removeUploadedFiles(@RequestBody fileList: List<String>): ResponseData {
        this.fileHandleService.removeFilesFromUploadedDir(fileList)
        return ResponseData.success()
    }


    @PostMapping(value = ["backend-handle"])
    fun backendHandler(@RequestBody fileHandleDto: FileBackendHandleDto): ResponseData {
        backendHandlingService.execFileBackendHandle(fileHandleDto)
        return ResponseData.success()
    }
}