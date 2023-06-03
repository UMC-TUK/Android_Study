package com.study.umc_chapter9_mission.retrofit.retrofit_interface

import com.study.umc_chapter9_mission.BuildConfig
import com.study.umc_chapter9_mission.retrofit.data.Typhoo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TyphooRetrofitInterface {
    companion object {
        private const val authKey = BuildConfig.SERVICE_KEY
    }

    @GET("getTyphoonInfo")
    fun getApi(
        @Query("serviceKey") serviceKey : String = authKey,
        @Query("pageNo") pageNo : Int = 1,
        @Query("numOfRows") numOfRows : Int = 10,
        @Query("dataType") returnType: String = "JSON",
        @Query("fromTmFc") fromTmFc : String = "20230531",
        @Query("toTmFc") toTmFc : String = "20230603"
    ): Call<Typhoo>
}