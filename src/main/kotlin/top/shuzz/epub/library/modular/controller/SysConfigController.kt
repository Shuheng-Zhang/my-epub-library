package top.shuzz.epub.library.modular.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.shuzz.epub.library.commons.response.ResponseData
import top.shuzz.epub.library.modular.dto.SysConfigDto
import top.shuzz.epub.library.modular.dto.SysConfigQueryDto
import top.shuzz.epub.library.modular.service.SysConfigService
import javax.annotation.Resource

/**
 * @author heng
 * @since 2022/4/10
 */
@RestController
@RequestMapping(value = ["/sys-config"])
class SysConfigController {

    @Resource
    private lateinit var sysConfigService: SysConfigService


    @PostMapping(value = ["list"])
    fun getSysConfigs(@RequestBody queryDto: SysConfigQueryDto): ResponseData {
        return ResponseData.success(this.sysConfigService.listSysConfigs(queryDto))
    }

    @PostMapping(value = ["create"])
    fun createSysConfig(@RequestBody configList: MutableList<SysConfigDto>): ResponseData {
        this.sysConfigService.createSysConfigs(configList)
        return ResponseData.success()
    }

    @PutMapping(value = ["update"])
    fun updateSysConfig(@RequestBody sysConfigDto: SysConfigDto): ResponseData {
        this.sysConfigService.updateSysConfig(sysConfigDto)
        return ResponseData.success()
    }

    @DeleteMapping(value = ["delete"])
    fun removeSysConfigs(@RequestBody idList: MutableList<String>): ResponseData {
        this.sysConfigService.removeSysConfig(idList)
        return ResponseData.success()
    }
}