package com.study.umc_chapter8.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary_table")
data class Dictionary(
    @PrimaryKey var id: Int?,
    @ColumnInfo(name = "english") var english : String,
    @ColumnInfo(name = "korean") var korean : String
)
