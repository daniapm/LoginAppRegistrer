package com.example.loginappregistrer.data.repository

import com.example.loginappregistrer.data.db.RegisterDao
import com.example.loginappregistrer.data.db.mapToDomain
import com.example.loginappregistrer.domain.model.Users
import com.example.loginappregistrer.domain.repository.LogRegisterRepository
import javax.inject.Inject
import com.example.loginappregistrer.utils.Result

class LogRegisterRepositoryImp @Inject constructor(
    private val registerDao: RegisterDao
) : LogRegisterRepository {

    override suspend fun getUsers(): Result<List<Users>, java.lang.Exception> = try {
        val users = registerDao.getAllUser()
        registerDao.insertAll(users)
        Result.Success(registerDao.getAllUser().map { user -> user.mapToDomain() })
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
