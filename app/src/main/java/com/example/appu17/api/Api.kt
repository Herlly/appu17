package com.example.appu17.api
import domain.ComicImage
import domain.DetailComic
import domain.RankComicList
import domain.SearchComic
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("list/getRankComicList")//获取排行榜
    suspend fun getRankComicList(@Query("page")page :Int):RankComicList
    @GET("comic/chapterNew")//获取章节图片
        suspend fun getComicImage(@Query("chapter_id")id:String):ComicImage
    @GET("comic/detail_static_new?v=4500102")
    suspend fun getDetailComic(@Query("comicid")comicid:String):DetailComic
    @GET("search/searchResult")
    suspend fun getSearchResult(@Query("q")name:String):SearchComic
}