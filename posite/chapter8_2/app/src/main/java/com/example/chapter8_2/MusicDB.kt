package com.example.chapter8_2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MusicEntity::class], version = 1)
abstract class MusicDB: RoomDatabase() {
    abstract fun MusicDao() : MusicDao

    companion object{
        private var instance : MusicDB? = null

        @Synchronized
        fun getInstance(context: Context) : MusicDB? {
            if (instance == null){
                synchronized(MusicDB::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MusicDB::class.java,
                        "user-database"
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return  instance
        }
    }
}