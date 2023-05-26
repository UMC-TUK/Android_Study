package com.study.umc_chapter9.retrofit.retrofit_interface

import com.study.umc_chapter9.retrofit.data.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface retrofit {
    @GET("comments/{id}")
    fun test_api_get(@Path("id") id : String): Call<DataModel>
}