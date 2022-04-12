package top.shuzz.epub.library.modular.dto

/**
 * @author heng
 * @since 2022/4/11
 */
data class EpubMetaInfoDto(
    var bookTitle: String? = null,
    var bookAuthors: String? = null,
    var bookDescription: String? = null,
    var epubUid: String? = null,
    var coverUrl: String? = null,
    var opfUrl: String? = null
) {
    override fun toString(): String {
        return "EpubMetaInfoDto(bookTitle=$bookTitle, bookAuthors=$bookAuthors, bookDescription=$bookDescription, epubUid=$epubUid, coverUrl=$coverUrl, opfUrl=$opfUrl)"
    }
}
