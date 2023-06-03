package com.study.umc_chapter9_mission.retrofit.data

import com.google.gson.annotations.SerializedName

data class Typhoo (
    val response: TyphooResponse
)

data class TyphooResponse (
    val header: TyphooHeader,
    val body: TyphooBody
)

data class TyphooBody (
    val dataType: String,
    val items: TyphooItems,
    val pageNo: Long,
    val numOfRows: Long,
    val totalCount: Long
)

data class TyphooItems (
    val item: List<TyphooItem>
)

data class TyphooItem (
    val other: String,
    val img: String,
    val rem: String,
    val tmFc: String,
    val tmSeq: Long,
    val typ15: Long,
    val typ15Ed: String,
    val typ15Er: Long,
    val typ25: Long,
    val typ25Ed: String,
    val typ25Er: Long,
    val typDir: String,
    val typEn: String,
    val typLat: Double,
    val typLoc: String,
    val typLon: Double,
    val typName: String,
    val typPS: Long,
    val typSeq: Long,
    val typSP: Long,
    val typTm: Long,
    val typWs: Long
)

data class TyphooHeader (
    val resultCode: String,
    val resultMsg: String
)
