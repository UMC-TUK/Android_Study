package com.study.umc_chapter8_mission.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo_table")
data class Memo(
    @PrimaryKey(autoGenerate = true) var id : Int? = null,
    @ColumnInfo var title:String,
    @ColumnInfo var context:String,
    @ColumnInfo var checked : Boolean = false,
    @ColumnInfo var like : Boolean = false,
    @ColumnInfo var bookmark : Boolean = false)
