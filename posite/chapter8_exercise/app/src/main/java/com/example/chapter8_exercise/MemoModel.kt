package com.example.chapter8_exercise

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "MemoTable")
data class MemoModel(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var title: String?,
    var content: String?,
    var date: String,
    var time: String,
    var like: Boolean
):Serializable