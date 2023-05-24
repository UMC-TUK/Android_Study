package com.example.ch05_mission.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "MemoEntity")
data class MemoEntity(
    @PrimaryKey
    @ColumnInfo(name = "title") val title : String,
    @ColumnInfo(name = "detail") val detail: String,
    @ColumnInfo(name = "liked") var liked : Boolean =false
):Serializable
