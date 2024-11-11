package com.example.movie.ui.stateHolders

sealed class StateHolder<T>(
    val isLoading: Boolean? = false,
    val data: T? = null,
    val errorCode: Int? = -1,
    val error: String? = ""
) {
    class Success<T>(data: T) : StateHolder<T>(data = data)
    class Loading<T>(isLoading: Boolean? = null) : StateHolder<T>(isLoading)
    class Error<T>(errorCode: Int = -1, error: String? = null) :
        StateHolder<T>(errorCode = errorCode, error = error ?: "")
}