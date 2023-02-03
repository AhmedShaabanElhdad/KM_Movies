package com.example.km_movie_app.di

import com.github.aakira.napier.Napier
import io.ktor.client.engine.*
import io.ktor.client.engine.ios.*


import com.github.aakira.napier.DebugAntilog
import io.ktor.client.*
import io.ktor.client.engine.ios.*

actual class HttpEngineProvider actual constructor() {
    actual val engine: HttpClientEngine = Ios.create{
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
}

actual fun initLogger() {
    Napier.base(DebugAntilog())
}