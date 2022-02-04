package com.example.loginappregistrer.domain.repository

import com.example.loginappregistrer.domain.model.Users

interface LogRegisterRepository {
    suspend fun getUsers(): Result<List<Users>>
}
