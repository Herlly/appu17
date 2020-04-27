package domain

data class ComicImage(
    val code: Int,
    val `data`: Data
){

    data class Data(
        val message: String,
        val returnData: ReturnData,
        val stateCode: Int
    )

    data class ReturnData(
        val chapter_id: String,
        val image_list: MutableList<Image>,
        val type: String,
        val unlock_image: List<Any>,
        val zip_file_high: String
    )

    data class Image(
        val height: String,
        val imHeightArr: List<String>,
        val image_id: String,
        val images: List<ImageX>,
        val img05: String,
        val img50: String,
        val location: String,
        val total_tucao: String,
        val type: String,
        val webp: String,
        val width: String
    )

    data class ImageX(
        val height: String,
        val id: String,
        val img05: String,
        val img50: String,
        val sort: String,
        val width: String
    )
}
