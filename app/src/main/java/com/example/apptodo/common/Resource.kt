package com.example.apptodo.common

sealed class Resource<T>(val data: T? = null, val errMessage: String? = null) {
    class Loading<T>() : Resource<T>()
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(data: T? = null, message: String?) : Resource<T>(data, message)

}
