package com.comst19.chapter08mission.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MemoEntity::class], version = 1) // 조건1
abstract class AppDatabase : RoomDatabase(){ // 조건2

    abstract fun getMemoDao() : MemoDao

    companion object {
        val databaseName = "db"
        var appDatabase : AppDatabase? = null

        fun getInstance(context: Context) :AppDatabase? {
            if (appDatabase == null){
                appDatabase = Room.databaseBuilder(context,
                    AppDatabase::class.java,
                    databaseName).
                fallbackToDestructiveMigration()
                    .build()
            }

            return appDatabase
        }
    }
}