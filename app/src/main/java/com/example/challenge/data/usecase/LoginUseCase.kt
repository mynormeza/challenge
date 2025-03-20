package com.example.challenge.data.usecase
import arrow.core.Either
import com.example.challenge.data.models.ErrorType
import com.example.challenge.data.models.SuccessResponse

interface LoginUseCase {
    fun executeLogin(
        username: String,
        password: String,
    ): Either<SuccessResponse<Int?>, ErrorType>
}
