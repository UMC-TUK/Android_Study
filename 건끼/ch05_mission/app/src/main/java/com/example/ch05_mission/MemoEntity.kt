package com.example.ch05_mission

import android.os.Parcelable
import java.io.Serializable

data class MemoEntity(
    val title : String,
    val detail: String
):Serializable
