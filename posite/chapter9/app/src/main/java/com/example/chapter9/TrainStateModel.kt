package com.example.chapter9

data class TrainStateModel(
    val currentCount: Int,
    val `data`: List<Data>,
    val numOfRows: String,
    val pageNo: String,
    val resultCode: Int,
    val resultMsg: String,
    val totalCount: Int
)