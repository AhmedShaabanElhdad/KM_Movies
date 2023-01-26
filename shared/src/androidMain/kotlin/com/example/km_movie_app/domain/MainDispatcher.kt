package com.example.km_movie_app.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class MainDispatcher{
    actual val dispatcher: CoroutineDispatcher
        get() = Dispatchers.Main
}