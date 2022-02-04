package com.example.loginappregistrer.domain.repository

import com.example.loginappregistrer.domain.model.Users
import com.example.loginappregistrer.utils.Result
import java.lang.Exception

interface LogRegisterRepository {
    suspend fun getUsers(): Result<List<Users>, Exception>
}
