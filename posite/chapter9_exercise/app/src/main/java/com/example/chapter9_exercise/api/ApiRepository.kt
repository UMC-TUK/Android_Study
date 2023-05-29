package com.example.chapter9_exercise.api

import android.util.Log
import com.example.chapter9_exercise.BuildConfig
import com.example.chapter9_exercise.airmodel.AirModel
import com.example.chapter9_exercise.coronamodel.CoronaStateModel
import com.example.chapter9_exercise.weathermodel.UltraShortModel
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
    private var api = retrofit.create(OpenAPIs::class.java)

    fun shortWeather(page: Int, date: String, time: String): UltraShortModel? {
        val queryMap = mutableMapOf<String, String>()
        queryMap.put("serviceKey", BuildConfig.surveyKey1)
        queryMap.put("numOfRows", "20")
        queryMap.put("pageNo", page.toString())
        queryMap.put("base_date", date)
        queryMap.put("base_time", time)
        queryMap.put("nx", "37")
        queryMap.put("ny", "127")
        queryMap.put("dataType", "JSON")
        val weather = api.ultraShortest(queryMap)
        try {
            val result = weather.execute()
            if(result.isSuccessful) {
                return result.body()
            }
        } catch (e: Exception) {
            Log.d("result", "result: ${e.message}")
            return null
        }
        return null
    }

    fun airCondition(page: Int): AirModel? {
        val queryMap = mutableMapOf<String, String>()
        queryMap.put("serviceKey", BuildConfig.surveyKey1)
        queryMap.put("numOfRows", "100")
        queryMap.put("pageNo", page.toString())
        queryMap.put("returnType", "json")
        queryMap.put("dataGubun", "HOUR")
        queryMap.put("itemCode", "PM10")

        val weather = api.getAir(queryMap)
        try {
            val result = weather.execute()
            if(result.isSuccessful) {
                return result.body()
            }
        } catch (e: Exception) {
            Log.d("result", "result: ${e.message}")
            return null
        }
        return null
    }

    fun coronaState(): CoronaStateModel? {
        val queryMap = mutableMapOf<String, String>()
        queryMap.put("serviceKey", BuildConfig.surveyKey1)
        val weather = api.getCorona(queryMap)
        try {
            val result = weather.execute()
            if(result.isSuccessful) {
                return result.body()
            }
        } catch (e: Exception) {
            Log.d("result", "result: ${e.message}")
            return null
        }
        return null
    }
}