package com.study.umc_chapter9_mission.retrofit.retrofit_interface

import com.study.umc_chapter9_mission.BuildConfig
import com.study.umc_chapter9_mission.retrofit.data.Dust
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface DustRetrofitInterface {
    companion object {
        private const val authKey = BuildConfig.SERVICE_KEY
    }

    @GET("getUlfptcaAlarmInfo")
    fun getApi(
        @Query("serviceKey") serviceKey: String = authKey,
        @Query("returnType") returnType: String = "json",
        @Query("numOfRows") numOfRows: String = "10",
        @Query("pageNo") pageNo: String = "1",
        @Query("year") year: String = "2020"
    ): Call<Dust>
}