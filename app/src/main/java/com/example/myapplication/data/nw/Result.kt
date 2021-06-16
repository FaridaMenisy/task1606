package com.example.myapplication.data.nw

sealed class Result<out T> {
    object Loading:  Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error<out T>(val throwable: Throwable) : Result<T>()
}