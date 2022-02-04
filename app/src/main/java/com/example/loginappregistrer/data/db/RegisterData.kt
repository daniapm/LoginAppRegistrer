package com.example.loginappregistrer.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.loginappregistrer.domain.model.Users


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
    var password: String

    )

fun RegisterData.mapToDomain():  Users {
    return with(this) {
        Users(
        firstName = firstName,
        lastName = lastName,
        userName = userName,
        password = password
        )
    }
}
