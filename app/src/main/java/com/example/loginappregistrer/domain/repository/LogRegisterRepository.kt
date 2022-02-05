package com.example.loginappregistrer.domain.repository

import com.example.loginappregistrer.domain.model.Users
import com.example.loginappregistrer.utils.Result

interface LogRegisterRepository {
    suspend fun getUsers(): Result<Users, Exception>
}
