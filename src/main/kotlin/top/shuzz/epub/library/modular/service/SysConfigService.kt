package top.shuzz.epub.library.modular.service

import cn.hutool.core.bean.BeanUtil
import cn.hutool.core.util.StrUtil
import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import org.springframework.stereotype.Service
import top.shuzz.epub.library.commons.exception.ServiceException
import top.shuzz.epub.library.commons.response.enums.ErrorEnum
import top.shuzz.epub.library.modular.dto.SysConfigDto
import top.shuzz.epub.library.modular.dto.SysConfigQueryDto
import top.shuzz.epub.library.modular.entity.SysConfig
import top.shuzz.epub.library.modular.mapper.SysConfigMapper
import top.shuzz.epub.library.modular.vo.SysConfigVo

/**
 * @author heng
 * @since 2022/4/10
 */
@Service
class SysConfigService: ServiceImpl<SysConfigMapper, SysConfig>() {

    /**
     * 查询系统配置
     */
    fun listSysConfigs(sysConfigQueryDto: SysConfigQueryDto): MutableList<SysConfigVo> {
        sysConfigQueryDto.let { queryDto ->
            val queryWrapper = KtQueryWrapper(SysConfig())
            queryWrapper.likeRight(StrUtil.isNotBlank(queryDto.configKey), SysConfig::configKey, queryDto.configKey)
            queryWrapper.likeRight(StrUtil.isNotBlank(queryDto.configName), SysConfig::configName, queryDto.configName)

            val resultList = mutableListOf<SysConfigVo>()
            this.list(queryWrapper).forEach {
                resultList.add(SysConfigVo(it.id.toString(), it.configKey, it.configName, it.configValue, it.configDescription, it.createdTime, it.updateTime))
            }
            return resultList
        }
    }

    /**
     * 创建系统配置
     * @param configList 配置项列表
     */
    fun createSysConfigs(configList: MutableList<SysConfigDto>) {

        // 检查配置项键名是否为空
        val checkedNotEmpty = this.checkConfigKeyNotEmpty(configList)
        if (!checkedNotEmpty) {
            throw ServiceException(ErrorEnum.PARAMS_INVALID, "SysConfig.configKey Cannot be Empty.")
        }

        // 检查配置项键名是否存在重复
        val checkedDuplicated = this.checkConfigKeyDuplicated(configList)
        if (!checkedDuplicated) {
            throw ServiceException(ErrorEnum.DUPLICATED_OBJECT_REFUSED, "SysConfig.configKey Cannot be Duplicated.")
        }

        // 将配置写入数据库
        configList.forEach {
            val config = SysConfig()
            BeanUtil.copyProperties(it, config, "id")
            this.save(config)
        }
    }

    /**
     * 更新配置项(配置项值/描述/配置项名)
     */
    fun updateSysConfig(sysConfigDto: SysConfigDto) {
        sysConfigDto.id ?: throw ServiceException(ErrorEnum.PARAMS_INVALID, "SysConfig.id Cannot be Empty.")

        this.getById(sysConfigDto.id?.toLong())?.let { config ->
            sysConfigDto.configName?.let { config.configName = it }
            sysConfigDto.configValue?.let { config.configValue = it }
            if (StrUtil.isNotBlank(sysConfigDto.configDescription)) config.configDescription = sysConfigDto.configDescription

            this.updateById(config)
        }

    }

    /**
     * 移除配置项
     */
    fun removeSysConfig(idList: MutableList<String>) {
        if (idList.isNotEmpty()) {
            idList.forEach { this.removeById(it.toLong()) }
        }
    }

    /**
     * 检查配置项键名
     * @param configList 目标配置项列表
     * @return true-检查通过, false-键名存在空值
     */
    private fun checkConfigKeyNotEmpty(configList: MutableList<SysConfigDto>): Boolean {

        if (configList.isEmpty()) return false

        configList.forEach {
            it.configKey ?: return false
        }

        return true
    }

    /**
     * 检查配置键名
     * @param configList 目标配置项列表
     * @return true-检查通过, false-键名重复
     */
    private fun checkConfigKeyDuplicated(configList: MutableList<SysConfigDto>): Boolean {

        // 检查目标配置项列表中是否存在键名重复
        val keys = mutableSetOf<String?>()
        configList.forEach {
            keys.add(it.configKey)
        }
        if (keys.size != configList.size) return false

        // 检查目标配置项是否在系统中存在键名重复
        configList.forEach {
            val queryWrapper = KtQueryWrapper(SysConfig())
            queryWrapper.eq(SysConfig::configKey, it.configKey)
            val existed = this.baseMapper.exists(queryWrapper)
            if (existed) {
                return false
            }
        }
        return true
    }
}