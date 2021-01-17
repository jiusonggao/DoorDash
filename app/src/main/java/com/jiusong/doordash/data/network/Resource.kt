package com.jiusong.doordash.data.network

/**
 * Created by jiusong.gao on 1/17/21.
 */
data class Resource<out T>(val status: Status, val data: T?, val msg: String) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, "")
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, "")
        }
    }
}