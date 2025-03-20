package com.example.challenge.data.models

data class ApiResponse<T>(
    val result: T?,
    val error: ErrorType?,
)
