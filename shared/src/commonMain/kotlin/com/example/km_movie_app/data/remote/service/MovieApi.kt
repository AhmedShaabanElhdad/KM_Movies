package com.example.km_movie_app.data.remote.service

import com.example.km_movie_app.data.remote.response.MovieResponse

interface MovieApi {
    suspend fun getMovies():MovieResponse
}