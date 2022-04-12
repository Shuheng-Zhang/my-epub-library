package top.shuzz.epub.library.modular.dto

/**
 * ePub 文件元数据
 * @author heng
 * @since 2022/4/11
 */
data class EpubMetaInfoDto(
    /**
     * 书目标题
     */
    var bookTitle: String? = null,
    /**
     * 书目作者
     */
    var bookAuthors: String? = null,
    /**
     * 书目简介
     */
    var bookDescription: String? = null,
    /**
     * 书目 UUID
     */
    var epubUid: String? = null,
    /**
     * 书目封面URL
     */
    var coverUrl: String? = null,
    /**
     * 书目OPF文件URL
     */
    var opfUrl: String? = null
) {
    override fun toString(): String {
        return "EpubMetaInfoDto(bookTitle=$bookTitle, bookAuthors=$bookAuthors, bookDescription=$bookDescription, epubUid=$epubUid, coverUrl=$coverUrl, opfUrl=$opfUrl)"
    }
}
