package com.example.loginappregistrer.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val password: String
): Parcelable
