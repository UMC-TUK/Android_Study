package com.comst19.chapter08mission.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MemoEntity(
    @PrimaryKey(autoGenerate = true) var id : Int? = null,
    @ColumnInfo var memoContent : String,
    @ColumnInfo var memoDate : String,
    @ColumnInfo var good : Boolean
)
