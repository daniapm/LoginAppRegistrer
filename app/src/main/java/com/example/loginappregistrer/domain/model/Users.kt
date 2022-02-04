package com.example.loginappregistrer.domain.model


data class Users(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val password: String,
    val confirmPassword: String
)
