package com.example.km_movie_app.di

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface DispatcherProvider {
    val io: CoroutineContext
    val ui: CoroutineContext
}

class DispatcherProviderImpl : DispatcherProvider {
    override val io: CoroutineContext = Dispatchers.Default
    override val ui: CoroutineContext = Dispatchers.Main
}