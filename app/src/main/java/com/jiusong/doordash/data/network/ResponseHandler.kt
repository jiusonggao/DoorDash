package com.jiusong.doordash.data.network

import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException
import javax.inject.Inject

/**
 * Created by jiusong.gao on 1/17/21.
 */

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1)
}

open class ResponseHandler @Inject constructor() {

    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(getErrorMsg(e.code()), null)
            is SocketTimeoutException -> Resource.error(getErrorMsg(ErrorCodes.SocketTimeOut.code), null)
            else -> Resource.error(getErrorMsg(Int.MAX_VALUE), null)
        }
    }

    fun <T : Any> handleError(error: String): Resource<T> {
        return Resource.error(error, null)
    }

    private fun getErrorMsg(code: Int): String {
        return when(code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not Found"
            else -> "Something went wrong"
        }
    }
}