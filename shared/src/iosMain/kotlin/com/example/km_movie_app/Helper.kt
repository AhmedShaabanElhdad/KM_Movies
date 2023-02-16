package com.example.km_movie_app

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GreetingHelper : KoinComponent {
    private val greeting : Greeting by inject()
    fun greet() : String = greeting.greeting()
}

