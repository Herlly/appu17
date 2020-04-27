package domain

data class DetailComic(
    val code: Int,
    val `data`: Data
){

    data class Data(
        val message: String,
        val returnData: ReturnData,
        val stateCode: Int
    )

    data class ReturnData(
        val chapter_list: MutableList<Chapter>,
        val comic: Comic,
        val comment: Comment,
        val otherWorks: List<OtherWork>,
        val tongRenCover: String
    )

    data class Chapter(
        val chapterIndex: Int,
        val chapter_id: String,
        val has_locked_image: Boolean,
        val image_total: String,
        val index: String,
        val is_new: Int,
        val name: String,
        val pass_time: Int,
        val publish_time: Int,
        val release_time: String,
        val size: String,
        val smallPlaceCover: String,
        val type: Any,
        val vip_images: String,
        val zip_high_webp: String
    )

    data class Comic(
        val accredit: Int,
        val affiche: String,
        val author: Author,
        val cate_id: String,
        val classifyTags: List<ClassifyTag>,
        val comic_id: String,
        val cover: String,
        val description: String,
        val is_vip: Int,
        val last_update_time: Int,
        val last_update_week: String,
        val name: String,
        val ori: String,
        val series_status: Int,
        val short_description: String,
        val status: Int,
        val tagList: List<String>,
        val theme_ids: List<String>,
        val thread_id: String,
        val ticket_desc: String,
        val type: String,
        val week_more: String,
        val wideColor: String,
        val wideCover: String
    )

    data class Comment(
        val commentCount: Int,
        val commentList: List<CommentX>
    )

    data class OtherWork(
        val comicId: String,
        val coverUrl: String,
        val name: String,
        val passChapterNum: String
    )

    data class Author(
        val avatar: String,
        val id: String,
        val name: String
    )

    data class ClassifyTag(
        val argName: String,
        val argVal: Int,
        val name: String
    )

    data class CommentX(
        val cate: String,
        val comic_id: Int,
        val comment_id: String,
        val content: String,
        val content_filter: String,
        val create_time: String,
        val create_time_str: String,
        val floor: String,
        val gift_img: String,
        val gift_num: Int,
        val imageList: List<Any>,
        val is_choice: String,
        val is_delete: String,
        val is_up: String,
        val object_type: String,
        val praise_total: Int,
        val thread_id: String,
        val ticketNum: Int,
        val ticket_id: String,
        val total_reply: String,
        val user: User,
        val user_id: Int
    )

    data class User(
        val face: String,
        val grade: Int,
        val group_user: String,
        val is_author: Int,
        val nickname: String,
        val other_comic_author: Int,
        val user_title: String,
        val vip_level: Int
    )
}
