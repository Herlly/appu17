package domain

data class SearchComic(
    val code: Int,
    val `data`: Data
){

    data class Data(
        val message: String,
        val returnData: ReturnData,
        val stateCode: Int
    )

    data class ReturnData(
        val comicNum: Int,
        val comics: MutableList<Comic>,
        val hasMore: Boolean,
        val page: Int,
        val searchData: List<Any>
    )

    data class Comic(
        val author: String,
        val clickTotal: String,
        val comicId: String,
        val conTag: Long,
        val cover: String,
        val description: String,
        val flag: Any,
        val monthTicket: String,
        val name: String,
        val passChapterNum: String,
        val seriesStatus: String,
        val tags: List<String>
    )
}
