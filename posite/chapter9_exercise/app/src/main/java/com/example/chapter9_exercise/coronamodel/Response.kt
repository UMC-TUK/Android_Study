package com.example.chapter9_exercise.coronamodel

data class Response(
    val result: List<Result>,
    val resultCnt: String,
    val resultCode: String,
    val resultMsg: String
)