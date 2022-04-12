package top.shuzz.epub.library.modular.controller

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.shuzz.epub.library.commons.response.ResponseData
import top.shuzz.epub.library.commons.util.SpringContextHolder
import top.shuzz.epub.library.modular.vo.AsyncTaskExecutorInfoVo

/**
 * @author heng
 * @since 2022/4/12
 */
@RestController
@RequestMapping(value = ["/async-task-executor"])
class AsyncTaskExecutorController {
    @GetMapping
    fun getExecutorInfo(): ResponseData {
        val executor = SpringContextHolder.getBean("async-task-executor") as ThreadPoolTaskExecutor
        val taskExecutor = executor.threadPoolExecutor
        val taskCount = taskExecutor.taskCount
        val completedCount = taskExecutor.completedTaskCount
        return ResponseData.success(AsyncTaskExecutorInfoVo(taskCount, completedCount))
    }
}