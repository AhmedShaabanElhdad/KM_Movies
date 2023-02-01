package com.example.km_movie_app.domain.core

sealed class Resource<out T>{
    data class Success<out T>(val result:T):Resource<T>()
    data class Error(val message:String):Resource<Nothing>()

    fun toData(): T? = if(this is Success) this.result else null
}
