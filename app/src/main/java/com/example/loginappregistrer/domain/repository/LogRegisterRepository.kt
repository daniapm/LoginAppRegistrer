package com.example.loginappregistrer.domain.repository

import com.example.loginappregistrer.domain.model.User
import com.example.loginappregistrer.utils.Result

interface LogRegisterRepository {
    suspend fun getUsers(): Result<List<User>, Exception>
}
