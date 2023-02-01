package com.example.km_movie_app.domain

import kotlinx.coroutines.CoroutineDispatcher


expect class MainDispatcher() {
    val dispatcher: CoroutineDispatcher
}