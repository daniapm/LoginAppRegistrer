package com.example.loginappregistrer.data.di.provider

import com.example.loginappregistrer.data.remote.services.LogRegisterRepositoryApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceFactory {

    private const val BASE_URL = "https://61fd4346f62e220017ce440c.mockapi.io/api/v1/"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

    @Singleton
    @Provides
    fun provideLogRegisterApi(): LogRegisterRepositoryApi =
        provideRetrofit().create(LogRegisterRepositoryApi::class.java)
}
