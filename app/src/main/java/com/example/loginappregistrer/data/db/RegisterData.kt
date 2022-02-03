package com.example.loginappregistrer.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "registerData")
data class RegisterData(

    @PrimaryKey(autoGenerate = true)
    var UserId: Int = 0,

    @ColumnInfo(name = "first_name")
    var firstName: String,

    @ColumnInfo(name = "last_name")
    var lastName: String,

    @ColumnInfo(name = "user_name")
    var userName: String,

    @ColumnInfo(name = "password")
    var passwrd: String,

    @ColumnInfo(name = "confirm_password")
    var confirmPassword: String,

    )
