package com.example.ch05_mission

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ch05_mission.dao.MemoDao
import com.example.ch05_mission.entity.MemoEntity

@Database(entities = [MemoEntity::class], version = 2)
abstract class AppDatabase :RoomDatabase() {
    abstract fun memoDao() : MemoDao
}