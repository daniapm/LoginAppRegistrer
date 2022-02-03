package com.example.loginappregistrer.data.di.provider

import android.content.Context
import androidx.room.Room
import com.example.loginappregistrer.data.db.AppDatabase
import com.example.loginappregistrer.data.db.RegisterDao
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
object ModuleDB {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "RegisterDb"
        ).build()
    }

    @Provides
    fun provideRegisterDao(database: AppDatabase): RegisterDao {
        return database.registerDao()
    }
}
