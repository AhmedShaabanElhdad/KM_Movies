package com.example.km_movie_app.di

import io.ktor.client.engine.*

expect class HttpEngineProvider() {
    val engine: HttpClientEngine
}

expect fun initLogger()