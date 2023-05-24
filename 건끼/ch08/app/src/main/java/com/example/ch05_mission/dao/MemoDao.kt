package com.example.ch05_mission.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ch05_mission.entity.MemoEntity

@Dao
interface MemoDao {
    @Query("SELECT * FROM MemoEntity")
    fun getAll() : List<MemoEntity>

    @Insert
    fun insertMemo(memoEntity : MemoEntity)

    @Query("DELETE FROM MemoEntity WHERE title=:title")
    fun deleteMemo(title: String)
}