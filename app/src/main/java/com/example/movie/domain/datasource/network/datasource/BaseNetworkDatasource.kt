package com.example.movie.domain.datasource.network.datasource

import android.accounts.NetworkErrorException
import com.example.movie.domain.common.Result
import retrofit2.Response

interface  BaseNetworkDataSource {
    suspend fun <T> getResult(call: suspend ()-> Response<T>): com.example.movie.domain.common.Result<T> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()

                if (body != null)
                    Result.Success(body)
                else
                    Result.Error(" ${response.code()} ${response.message()}")

            } else Result.Error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            when (e) {
                is NetworkErrorException -> Result.Error(e.message ?: e.toString())
                else -> Result.Error(e.message ?: e.toString())
            }
        }
    }

}