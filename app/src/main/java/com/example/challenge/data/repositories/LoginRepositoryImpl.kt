package com.example.challenge.data.repositories

import arrow.core.Either
import com.example.challenge.data.models.ErrorType
import com.example.challenge.data.models.SuccessResponse
import com.example.challenge.data.source.network.ApiService

class LoginRepositoryImpl : LoginRepository {

    private val apiService = ApiService()

    override fun login(
        username: String,
        password: String
    ): Either<SuccessResponse<Int?>, ErrorType> {
        return apiService.login(username, password)
    }

}