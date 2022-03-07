package com.example.loginappregistrer.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.loginappregistrer.domain.model.User


@Entity(tableName = "registerData")
data class RegisterData(

    @PrimaryKey(autoGenerate = true)
    var userId: Int = 0,

    @ColumnInfo(name = "first_name")
    var firstName: String,

    @ColumnInfo(name = "last_name")
    var lastName: String,

    @ColumnInfo(name = "user_name")
    var userName: String,

    @ColumnInfo(name = "password")
    var password: String,

    @ColumnInfo(name = "balance")
    var balance: Int

)

fun RegisterData.mapToDomain(): User {
    return with(this) {
        User(
            firstName = firstName,
            lastName = lastName,
            userName = userName,
            password = password,
            balance = balance,
            id = userId
        )
    }
}
