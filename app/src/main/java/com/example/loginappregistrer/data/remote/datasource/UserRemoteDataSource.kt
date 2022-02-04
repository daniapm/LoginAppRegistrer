package com.example.loginappregistrer.data.remote.datasource

import com.example.loginappregistrer.data.di.provider.ServiceFactory
import com.example.loginappregistrer.data.remote.services.LogRegisterRepositoryApi
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor() {
    val logRegisterRepositoryApi: LogRegisterRepositoryApi by lazy {
        ServiceFactory.provideLogRegisterApi()

    }
}
