package com.example.km_movie_app.di

import com.example.km_movie_app.data.local.DatabaseDriverFactory
import com.example.km_movie_app.domain.MainDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { DatabaseDriverFactory() }
    single { MainDispatcher() }
}