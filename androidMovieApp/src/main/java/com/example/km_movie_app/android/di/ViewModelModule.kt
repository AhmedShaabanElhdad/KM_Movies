package com.example.km_movie_app.android.di

import com.example.km_movie_app.presentation.feature.MovieViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { MovieViewModel(get()) }
}