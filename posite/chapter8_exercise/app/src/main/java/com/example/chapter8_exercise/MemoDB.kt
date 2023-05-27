package com.example.chapter8_exercise

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MemoModel::class], version = 1)
abstract class MemoDB : RoomDatabase() {
    abstract fun MusicDao() : MemoDao

    companion object{
        private var instance : MemoDB? = null

        @Synchronized
        fun getInstance(context: Context) : MemoDB? {
            if (instance == null){
                synchronized(MemoDB::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MemoDB::class.java,
                        "user-database"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return  instance
        }
    }
}