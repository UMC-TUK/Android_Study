package com.study.umc_chapter9_mission.retrofit

import com.google.gson.GsonBuilder
import com.study.umc_chapter9_mission.retrofit.retrofit_interface.TyphooRetrofitInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TyphooRetrofit {
    private const val BASE_URL = "http://apis.data.go.kr/1360000/TyphoonInfoService/"

    fun getApiService(): TyphooRetrofitInterface? {
        return getInstance()?.create(TyphooRetrofitInterface::class.java)
    }

    private fun getInstance(): Retrofit? {
        val gson = GsonBuilder().setLenient().create()

        val httpClient = OkHttpClient.Builder()

        // HttpLoggingInterceptor 추가
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(loggingInterceptor)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
    }
}