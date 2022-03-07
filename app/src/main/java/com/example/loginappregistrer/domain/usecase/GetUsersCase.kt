package com.example.loginappregistrer.domain.usecase

import com.example.loginappregistrer.domain.model.User
import com.example.loginappregistrer.domain.repository.LogRegisterRepository
import com.example.loginappregistrer.utils.Result
import javax.inject.Inject

class GetUsersCase @Inject constructor(private val logRegisterRepository: LogRegisterRepository) {
    suspend operator fun invoke(): Result<List<User>, Exception> = logRegisterRepository.
    getUsers()
}
