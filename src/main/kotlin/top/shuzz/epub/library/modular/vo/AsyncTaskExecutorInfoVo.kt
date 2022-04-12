package top.shuzz.epub.library.modular.vo

/**
 * 异步执行器信息对象
 * @author heng
 * @since 2022/4/12
 */
data class AsyncTaskExecutorInfoVo(
    var taskCount: Long? = null,
    var completedCount: Long? = null
)
