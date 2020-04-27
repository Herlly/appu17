package com.example.appu17.api

import domain.ComicImage
import domain.DetailComic
import domain.RankComicList
import domain.SearchComic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object GetData {
     suspend fun getSearchComic(name:String): SearchComic {
        return withContext(Dispatchers.Main){
            try {
                val api= ApiClient.getClient
                val searchComic=api.getSearchResult(name)
                searchComic
            }catch (e:Throwable){
                throw e
            }
        }
    }
   suspend fun getDetailComic(comic_Id:String): DetailComic {
        return withContext(Dispatchers.Main){
            try {
                val api= ApiClient.getClient
                var detailComic=api.getDetailComic(comic_Id)
                detailComic
            }catch (e:Throwable){
                throw e
            }
        }
    }
     suspend fun getComicImage(chapter_Id:String): ComicImage {
        return withContext(Dispatchers.Main){
            try {
                val api= ApiClient.getClient
                var detailComic=api.getComicImage(chapter_Id)
                detailComic
            }catch (e:Throwable){
                throw e
            }
        }
    }
     suspend fun getRankComicList(page:Int): RankComicList {
        return withContext(Dispatchers.Main){
            try {
                val api=ApiClient.getClient
                var rankComicList=api.getRankComicList(page)
                rankComicList
            }catch (e:Throwable){
                throw e
            }
        }
    }

}