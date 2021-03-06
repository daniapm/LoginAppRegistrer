package com.example.loginappregistrer.data.repository

import com.example.loginappregistrer.data.db.RegisterDao
import com.example.loginappregistrer.data.db.mapToDomain
import com.example.loginappregistrer.data.remote.datasource.UserRemoteDataSource
import com.example.loginappregistrer.data.remote.model.mapToData
import com.example.loginappregistrer.domain.model.User
import com.example.loginappregistrer.domain.repository.LogRegisterRepository
import com.example.loginappregistrer.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LogRegisterRepositoryImp @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val registerDao: RegisterDao
) : LogRegisterRepository {

    override suspend fun getUsers(): Result<List<User>, Exception> = try {
        withContext(Dispatchers.IO) {
            val users = userRemoteDataSource.logRegisterRepositoryApi.getUsers().map { it.mapToData() }
            registerDao.insertAll(users)
            Result.Success(registerDao.getAllUser().map { user -> user.mapToDomain() })
        }
    } catch (e: Exception) {
        Result.Error(e)
    }
}
