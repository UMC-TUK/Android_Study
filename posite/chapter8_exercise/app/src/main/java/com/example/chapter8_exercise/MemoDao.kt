package com.example.chapter8_exercise

import androidx.room.*

@Dao
interface MemoDao {
    @Insert
    fun insert(music: MemoModel)

    @Delete
    fun delete(music: MemoModel)

    @Update
    fun update(music: MemoModel)

    @Query("SELECT * from MemoTable")
    fun getMemos(): List<MemoModel>

    @Query("SELECT * from MemoTable where id=:id")
    fun getMemo(id: Int): MemoModel
}