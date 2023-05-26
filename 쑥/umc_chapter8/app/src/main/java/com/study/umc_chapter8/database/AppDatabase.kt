package com.study.umc_chapter8.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.study.umc_chapter8.dao.DictionaryDao
import com.study.umc_chapter8.entity.Dictionary

@Database(entities = [Dictionary::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dictionaryDao() : DictionaryDao
}