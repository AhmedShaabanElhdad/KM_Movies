package com.example.km_movie_app.di

import com.github.aakira.napier.DebugAntilog
import com.github.aakira.napier.Napier
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import java.util.concurrent.TimeUnit

actual class HttpEngineProvider actual constructor() {
    actual val engine: HttpClientEngine
        get() = OkHttp.create{
            config {
                retryOnConnectionFailure(true)
                connectTimeout(5, TimeUnit.SECONDS)
            }
        }
}


actual fun initLogger() {
    Napier.base(DebugAntilog())
}