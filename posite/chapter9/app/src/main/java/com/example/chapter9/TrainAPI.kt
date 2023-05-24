package com.example.chapter9

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface TrainAPI {
    @GET("delay")
    fun getTrainInfo(@QueryMap query: Map<String, String>) : Call<TrainStateModel>
}