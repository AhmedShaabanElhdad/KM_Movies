package com.example.km_movie_app

import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertTrue

class CommonGreetingTest :KoinTest{

    private val greeting:Greeting by inject()
    @Test
    fun testExample() {
        assertTrue(greeting.greeting().contains("Hello"), "Check 'Hello' is mentioned")
    }
}