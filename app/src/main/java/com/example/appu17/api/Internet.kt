package com.example.appu17.api

import android.content.Context
import android.net.ConnectivityManager

object Internet {
    fun  isNetworkConnected(context: Context):Boolean{
        if(context!=null){
            val connection=context.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager
            val mid =connection.activeNetworkInfo
            if(mid!=null)return mid.isAvailable
        }
        return false
    }
}