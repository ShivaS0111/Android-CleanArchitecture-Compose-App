package com.example.movie.core.util

sealed class Result<T>(val data:T?=null, val message: String?=null){

    class Loading<T> : Result<T>()

    class Success<T>(data : T) : Result<T>(data = data)

    class Error<T>(message : String?) : Result<T>(message = message)
}