package com.study.umc_chapter8_mission.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.study.umc_chapter8_mission.database.dao.MemoDao
import com.study.umc_chapter8_mission.database.entity.Memo

@Database(entities = [Memo::class], version = 1)
abstract class MemoDatabase : RoomDatabase() {
    abstract fun memoDao() : MemoDao

    companion object{
        private var INSTANCE: MemoDatabase? = null
        fun getInstance(context: Context): MemoDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MemoDatabase::class.java,
                    "memo_database"
                ).build()
            }
            return INSTANCE as MemoDatabase
        }
    }
}