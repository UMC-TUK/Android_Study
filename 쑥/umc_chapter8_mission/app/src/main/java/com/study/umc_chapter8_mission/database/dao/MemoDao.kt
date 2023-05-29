package com.study.umc_chapter8_mission.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.study.umc_chapter8_mission.database.entity.Memo

@Dao
interface MemoDao {
    @Query("SELECT * FROM memo_table")
    fun getAll() : MutableList<Memo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg dictionary: Memo)

    @Delete
    fun delete(memo: Memo)

    @Update
    fun update(memo: Memo)
}