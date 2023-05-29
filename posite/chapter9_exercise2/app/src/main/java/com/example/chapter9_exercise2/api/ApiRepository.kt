package com.example.chapter9_exercise2.api

import android.util.Log
import com.example.chapter9_exercise2.BuildConfig
import com.example.chapter9_exercise2.model.TokenModel
import com.example.chapter9_exercise2.model.UserInfoModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIRepository {
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .retryOnConnectionFailure(false)
        .build()

    private val retrofit = Retrofit.Builder().baseUrl(BuildConfig.baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var api = retrofit.create(APIs::class.java)

    fun getAccessToken(code: String): TokenModel? {
        val tokenResult: TokenModel? = null
        val queryMap = mutableMapOf<String, String>()
        queryMap.put("client_id", BuildConfig.id)
        queryMap.put("client_secret", BuildConfig.secret)
        queryMap.put("code", code)
        val token = api.getAccessToken(queryMap)
        return try{
            val result = token.execute()
            result.body()!!
        } catch (e: Exception) {
            tokenResult
        }
    }


    fun getUserInfo(token: String): UserInfoModel?{
        var userInfoes: UserInfoModel? = null


        val retro = Retrofit.Builder().baseUrl("https://api.github.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apis = retro.create(APIs::class.java)
        val userInfo = apis.getUserInfo("token $token")
        try{
            val result = userInfo.execute()
            if(result.isSuccessful) {
                userInfoes = result.body()!!
                return userInfoes
            }
        } catch(e: Exception) {
            Log.d("error", "error: ${e.message}")
            return userInfoes
        }
        return userInfoes
    }
}