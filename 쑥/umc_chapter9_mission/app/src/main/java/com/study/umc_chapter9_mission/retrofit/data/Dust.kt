package com.study.umc_chapter9_mission.retrofit.data

import com.google.gson.annotations.SerializedName

//data class Dust(
//    @SerializedName("response")
//    val response : DustResponse?
//
//)
//
//data class DustResponse(
//    @SerializedName("body")
//    val body : DustBody?,
//    @SerializedName("header")
//    val header : DustHeader?
//)
//
//data class DustBody(
//    @SerializedName("totalCount")
//    val totalCount : String?,
//    @SerializedName("items")
//    val items : List<DustItems>,
//    @SerializedName("pageNo")
//    val pageNo : Int?,
//    @SerializedName("numOfRows")
//    val numOfRows : Int?
//)
//
//data class DustHeader(
//    @SerializedName("resultMsg")
//    val resultMsg : String?,
//    @SerializedName("resultCode")
//    val resultCode : String?
//)
//
//data class DustItems(
//    @SerializedName("sn")
//    val sn : String?,
//    @SerializedName("dataDate")
//    val dataDate : String?,
//    @SerializedName("districtName")
//    val districtName : String?,
//    @SerializedName("moveName")
//    val moveName : String?,
//    @SerializedName("itemCode")
//    val itemCode : String?,
//    @SerializedName("issueGbn")
//    val issueGbn : String?,
//    @SerializedName("issueDate")
//    val issueDate : String?,
//    @SerializedName("issueTime")
//    val issueTime : String?,
//    @SerializedName("issueVal")
//    val issueVal : String?,
//    @SerializedName("clearDate")
//    val clearDate : String?,
//    @SerializedName("clearTime")
//    val clearTime : String?,
//    @SerializedName("clearVal")
//    val clearVal : String?
//)

data class Dust(
    val response: DustResponse
)

data class DustResponse(
    val body: DustBody,
    val header: DustHeader
)

data class DustBody(
    val totalCount: Int,
    val items: List<DustItem>,
    val pageNo: Int,
    val numOfRows: Int
)

data class DustItem(
    val clearVal: String,
    val sn: String,
    val districtName: String,
    val dataDate: String,
    val issueVal: String,
    val issueTime: String,
    val clearDate: String,
    val issueDate: String,
    val moveName: String,
    val clearTime: String,
    val issueGbn: String,
    val itemCode: String
)

data class DustHeader(
    val resultMsg: String,
    val resultCode: String
)