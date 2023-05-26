package com.study.umc_chapter9.retrofit.data

import com.google.gson.annotations.SerializedName

data class DataModel(
    @SerializedName("postId")
    val postId : String,
    @SerializedName("id")
    val id : String,
    @SerializedName("name")
    val name : String,
    @SerializedName("email")
    val email : String,
    @SerializedName("body")
    val body : String
)
