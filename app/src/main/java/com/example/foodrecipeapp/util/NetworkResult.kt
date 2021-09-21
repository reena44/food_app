package com.example.foodrecipeapp.util

sealed class NetworkResult<T> (
        val data:T? = null,
        val mesaage :String?= null
        ){

    class Success <T>(data: T):NetworkResult<T>(data)
    class Error<T>(mesaage: String?,data: T?= null):NetworkResult<T>(data,mesaage)
    class Loading<T>:NetworkResult<T>()

}