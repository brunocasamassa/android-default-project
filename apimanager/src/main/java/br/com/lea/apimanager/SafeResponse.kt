package br.com.lea.apimanager

import android.util.Log
import com.lea.commons.extensions.getOrSafe
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

sealed class SafeResponse<out T> {
    data class Success<out T>(val value: T) : SafeResponse<T>()
    data class GenericError(val code: Int? = null, val error: Response<*>? = null, val errorMessage: String) : SafeResponse<Nothing>()
    data class NetworkError(val errorMessage: String) : SafeResponse<Nothing>()
}

suspend fun <T> safeRequest(request: suspend () -> T): SafeResponse<T> {
    return try {
        SafeResponse.Success(request())
    } catch (throwable: Throwable) {
        Log.d(SafeResponse::class.java.toString(),throwable.message.toString())
        return when (throwable) {
            is IOException -> {
                SafeResponse.NetworkError(getErrorMessage(throwable.localizedMessage?:throwable.message.getOrSafe()))
            }
            is HttpException -> {
                SafeResponse.GenericError(
                    throwable.code(),
                    throwable.response(),
                    getErrorMessage(throwable.response()?.message())
                )
            }
            else -> {
                SafeResponse.GenericError(null, null, getErrorMessage(throwable.localizedMessage?:throwable.message.getOrSafe()))
            }
        }
    }
}

private fun getErrorMessage(response: String?): String = response?: "Ops. Tivemos um problema aqui. Por favor, tente novamente."


