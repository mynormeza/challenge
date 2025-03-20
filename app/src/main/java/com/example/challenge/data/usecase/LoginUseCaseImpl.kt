package com.example.challenge.data.usecase

import arrow.core.Either
import com.example.challenge.data.models.ErrorType
import com.example.challenge.data.models.SuccessResponse
import com.example.challenge.data.repositories.LoginRepositoryImpl

class LoginUseCaseImpl : LoginUseCase {
    private val loginRepository = LoginRepositoryImpl()

    override fun executeLogin(
        username: String,
        password: String,
    ): Either<SuccessResponse<Int?>, ErrorType> = loginRepository.login(username, password)
}
