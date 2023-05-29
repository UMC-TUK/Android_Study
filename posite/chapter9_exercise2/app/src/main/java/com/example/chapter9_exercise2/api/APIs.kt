package com.example.chapter9_exercise2.api

import com.example.chapter9_exercise2.model.TokenModel
import com.example.chapter9_exercise2.model.UserInfoModel
import retrofit2.Call
import retrofit2.http.*

interface APIs {

    @POST("access_token")
    @Headers("accept: application/json", "content-type: application/json")
    fun getAccessToken(@QueryMap query: Map<String, String>): Call<TokenModel>

    @GET("user")
    @Headers("accept: application/json", "content-type: application/json")
    fun getUserInfo(@Header("Authorization")token: String): Call<UserInfoModel>
}