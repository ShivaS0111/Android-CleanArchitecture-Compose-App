package com.example.movie.core.util

sealed class UIStateHolder<T>(
    val isLoading: Boolean? = false,
    val data: T? = null,
    val errorCode: Int? = -1,
    val error: String? = ""
) {
    class Success<T>(data: T) : UIStateHolder<T>(data = data)
    class Loading<T>(isLoading: Boolean? = null) : UIStateHolder<T>(isLoading)
    class Error<T>(errorCode: Int = -1, error: String? = null) :
        UIStateHolder<T>(errorCode = errorCode, error = error ?: "")
}