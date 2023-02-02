package com.example.km_movie_app

class Greeting(private val platform: Platform) {
    fun greeting(): String {
        return "Hello, $platform!"
    }
}