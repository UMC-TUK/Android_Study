package com.example.chapter9_exercise.api

import com.example.chapter9_exercise.airmodel.AirModel
import com.example.chapter9_exercise.coronamodel.CoronaStateModel
import com.example.chapter9_exercise.weathermodel.UltraShortModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface OpenAPIs {
    @GET("1360000/VilageFcstInfoService_2.0/getUltraSrtFcst")
    fun ultraShortest(@QueryMap query: Map<String, String>): Call<UltraShortModel>

    @GET("B552584/ArpltnStatsSvc/getCtprvnMesureLIst")
    fun getAir(@QueryMap query: Map<String, String>): Call<AirModel>

    @GET("1790387/covid19CurrentStatusKorea/covid19CurrentStatusKoreaJason")
    fun getCorona(@QueryMap query: Map<String, String>): Call<CoronaStateModel>

}