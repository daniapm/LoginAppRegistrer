package com.example.loginappregistrer.data.di.binds

import com.example.loginappregistrer.data.repository.LogRegisterRepositoryImp
import com.example.loginappregistrer.domain.repository.LogRegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LogRegisterModule {

    @Binds
    abstract fun bindLogRegisterRepository(
        logRegisterRepositoryImp: LogRegisterRepositoryImp
    ): LogRegisterRepository
}
