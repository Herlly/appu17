package com.example.appu17.api

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

object SaveFile {

     suspend fun getBitmap(context:Context,path:String):Bitmap{
        return withContext(Dispatchers.IO){
            try {
                val bitmap= Glide.with(context)
                    .asBitmap()
                    .load(path)
                    .into(context.resources.displayMetrics.widthPixels,context.resources.displayMetrics.heightPixels)
                    .get()
                bitmap
            }catch (e:Throwable){
                throw e
            }
        }
    }
    fun onSaveBitmap(bitmap: Bitmap, name:String):String{
        val filename= "data/data/com.example.appu17/$name.jpg"
        val file= File(filename)
        try {
            if(!file.exists()){
                file.createNewFile()
            }
            val fos= FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos)
            fos.flush()
            fos.close()
        }catch (e :Exception){
            throw e
        }
        return file.absolutePath
    }
}