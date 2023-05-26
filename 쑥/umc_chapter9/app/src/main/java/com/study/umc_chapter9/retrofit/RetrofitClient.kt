package com.study.umc_chapter9.retrofit

import com.google.gson.GsonBuilder
import com.study.umc_chapter9.retrofit.retrofit_interface.retrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun getApiService(): retrofit? {
        return getInstance()?.create(retrofit::class.java)
    }

    private fun getInstance(): Retrofit? {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}