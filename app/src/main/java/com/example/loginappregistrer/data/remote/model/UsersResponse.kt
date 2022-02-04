package com.example.loginappregistrer.data.remote.model

import com.example.loginappregistrer.data.db.RegisterData
import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @SerializedName("users") val users: List<UserResponse>
)
fun UsersResponse.mapToData(): List<RegisterData> {
    return with(this) {
        users.map {it.mapToData()}
    }
}
