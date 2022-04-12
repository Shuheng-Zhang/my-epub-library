package top.shuzz.epub.library.modular.vo

/**
 * 异步执行器信息对象
 * @author heng
 * @since 2022/4/12
 */
data class AsyncTaskExecutorInfoVo(
    /**
     * 任务总数
     */
    var taskCount: Long? = null,
    /**
     * 已完成任务数
     */
    var completedCount: Long? = null
)
