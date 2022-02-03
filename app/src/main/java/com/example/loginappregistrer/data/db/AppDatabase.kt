package com.example.loginappregistrer.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RegisterData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun registerDao() : RegisterDao
}
