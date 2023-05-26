package com.study.umc_chapter8.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.study.umc_chapter8.entity.Dictionary

@Dao
interface DictionaryDao {
    @Query("SELECT * FROM dictionary_table")
    fun getAll() : MutableList<Dictionary>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg dictionary: Dictionary)

    @Delete
    fun delete(dictionary: Dictionary)

    @Update
    fun update(dictionary: Dictionary)
}