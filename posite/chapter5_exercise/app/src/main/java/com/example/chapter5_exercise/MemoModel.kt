package com.example.chapter5_exercise

import java.io.Serializable

data class MemoModel(
    var title: String?,
    var content: String?,
    var date: String,
    var time: String
): Serializable