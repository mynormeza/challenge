package com.example.challenge.data.source.network
import arrow.core.Either
import com.example.challenge.data.models.ErrorType
import com.example.challenge.data.models.SuccessResponse

class ApiService {
    fun login(
        username: String,
        password: String,
    ): Either<SuccessResponse<Int?>, ErrorType> =
        when (username) {
            "user" -> {
                Either.Left(SuccessResponse(result = 1))
            }

            "wrong" -> {
                Either.Right(ErrorType.WRONG_CREDENTIALS)
            }

            "internal" -> {
                Either.Right(ErrorType.INTERNAL_SERVER_ERROR)
            }

            else -> {
                Either.Right(ErrorType.UNKNOWN_ERROR)
            }
        }
}
