package com.example.chapter9

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiRepository {
    val gson : Gson = GsonBuilder()
        .setLenient()
        .create()
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.MINUTES)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .retryOnConnectionFailure(false)
        .build()

    private val retrofit = Retrofit.Builder().baseUrl(BuildConfig.baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    private var api = retrofit.create(TrainAPI::class.java)

    fun getTrain(page: Int, line: Int): TrainStateModel? {
        var train: TrainStateModel? = null
        val query = mutableMapOf<String ,String>()
        query.put("serviceKey", BuildConfig.surveyKey)
        query.put("numOfRows", "10")
        query.put("pageNo", page.toString())
        query.put("lineNum", line.toString())
        val getInfo = api.getTrainInfo(query)
        try{
            val result = getInfo.execute()
            Log.d("result", "result: ${result.body()}")
            if(result.isSuccessful) {
                return result.body()!!
            }
        }catch (e: Exception) {
            Log.d("result", "result: ${e.message}")
            return null
        }
        return train
    }
}