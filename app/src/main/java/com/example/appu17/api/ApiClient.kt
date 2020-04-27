package com.example.appu17.api
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val getClient:Api
    get() {
        val interceptor=HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client=OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit =Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl("https://app.u17.com/v3/appV3_3/android/phone/")
            .build()
        return retrofit.create(Api::class.java)
    }
}