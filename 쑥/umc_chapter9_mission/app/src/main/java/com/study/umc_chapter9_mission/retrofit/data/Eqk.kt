package com.study.umc_chapter9_mission.retrofit.data

data class Eqk (
    val response: EqkResponse
)

data class EqkResponse (
    val header: EqkHeader,
    val body: EqkBody
)

data class EqkBody (
    val dataType: String,
    val items: EqkItems,
    val pageNo: Long,
    val numOfRows: Long,
    val totalCount: Long
)

data class EqkItems (
    val item: List<EqkItem>
)

data class EqkItem (
    val cnt: Long,
    val fcTp: Long,
    val img: String,
    val inT: String,
    val lat: Double,
    val loc: String,
    val lon: Double,
    val mt: Double,
    val rem: String,
    val stnID: Long,
    val tmEqk: Long,
    val tmFc: Long,
    val tmSeq: Long,
    val dep: Long
)

data class EqkHeader (
    val resultCode: String,
    val resultMsg: String
)
