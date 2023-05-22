package com.example.chapter8_2

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MusicTable")
data class MusicEntity(
    @PrimaryKey val name: String,
    val artist: String
)
