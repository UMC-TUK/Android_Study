package com.comst19.chapter08mission.db

import androidx.room.*

@Dao
interface MemoDao {

    @Query("SELECT * FROM MemoEntity ORDER BY id DESC")
    fun getAllMemo () : List<MemoEntity>

    @Insert
    fun insertMemo(memo : MemoEntity)

    @Update
    fun updateMemo(memo : MemoEntity)

    @Delete
    fun deleteMemo(memo : MemoEntity)
}