package com.example.loginappregistrer.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RegisterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(registerData : List<RegisterData>)

    @Query("SELECT * FROM registerData")
    fun getAllUser(): List<RegisterData>
}
