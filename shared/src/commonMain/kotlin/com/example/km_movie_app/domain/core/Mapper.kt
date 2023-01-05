package com.example.km_movie_app.domain.core

interface Mapper<in T, out E> {
    fun transfer(response: T): E
}