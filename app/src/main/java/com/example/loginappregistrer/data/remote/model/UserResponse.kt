package com.example.loginappregistrer.data.remote.model

import com.example.loginappregistrer.data.db.RegisterData
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("username") val userName: String,
    @SerializedName("password") val password: String
)

fun UserResponse.mapToData(): RegisterData {
    return with(this) {
            RegisterData(
                firstName = firstName,
                lastName = lastName,
                userName = userName,
                password = password
            )
    }
}
