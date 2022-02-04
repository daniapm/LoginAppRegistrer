package com.example.loginappregistrer.domain.usecase

import com.example.loginappregistrer.domain.model.Users
import com.example.loginappregistrer.domain.repository.LogRegisterRepository
import javax.inject.Inject
import com.example.loginappregistrer.utils.Result

class GetUsersCase @Inject constructor(private val logRegisterRepository: LogRegisterRepository) {
    suspend operator fun invoke(): Result<List<Users>, Exception> = logRegisterRepository.getUsers()
}
