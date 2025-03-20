package com.example.challenge.data.repositories

import arrow.core.Either
import com.example.challenge.data.models.ErrorType
import com.example.challenge.data.models.SuccessResponse

interface LoginRepository {
    fun login(username: String, password: String): Either<SuccessResponse<Int?>, ErrorType>
}