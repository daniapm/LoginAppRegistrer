package com.example.loginappregistrer.data.remote.services

import com.example.loginappregistrer.data.remote.model.UserResponse
import retrofit2.http.*

interface LogRegisterRepositoryApi {
    @GET("users")
    suspend fun getUsers(): List<UserResponse>

    @GET("users")
    suspend fun getLogin(
        @Query("userName") user_name: String,
        @Query("password") password: String
    ): UserResponse

    @FormUrlEncoded
    @POST("users")
    suspend fun registerUsers(
        @Field("first_name") firstName: String,
        @Field("last_name") lastName: String,
        @Field("username") userName: String,
        @Field("password") password: String
    ): UserResponse
}
