package com.example.chapter8_2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface  MusicDao {
    @Insert
    fun insert(music: MusicEntity)

    @Delete
    fun delete(music: MusicEntity)

    @Update
    fun update(music: MusicEntity)

    @Query("SELECT * from MusicTable")
    fun getMusics(): List<MusicEntity>

    @Query("SELECT * from MusicTable where name = :name")
    fun getMusic(name: String): MusicEntity

}