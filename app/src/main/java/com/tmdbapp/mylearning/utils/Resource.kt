package com.tmdbapp.mylearning.utils

sealed class Resource<out T> {
    class Loading<out T> : Resource<T>()
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure<out T>(val error: String = Constants.SERVER_ERROR) : Resource<T>()
}