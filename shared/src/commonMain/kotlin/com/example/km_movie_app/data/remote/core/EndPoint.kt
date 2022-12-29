package com.example.km_movie_app.data.remote.core

import com.example.km_movie_app.data.PropertiesProvider

private val BASE_URL: String by lazy {
    "https://api.themoviedb.org/3/movie/popular?api_key=${PropertiesProvider().getApiKey()}"
}

sealed class EndPoint(val url:String) {
    object GetAllMovies : EndPoint("$BASE_URL")
}