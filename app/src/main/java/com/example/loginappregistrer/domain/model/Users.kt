package com.example.loginappregistrer.domain.model

import android.accessibilityservice.GestureDescription
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val password: String,
    val balance: Int,
    val id: Int
): Parcelable

