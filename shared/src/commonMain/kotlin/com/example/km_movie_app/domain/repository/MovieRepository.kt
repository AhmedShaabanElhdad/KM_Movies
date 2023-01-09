package com.example.km_movie_app.domain.repository

import com.example.km_movie_app.domain.core.Resource
import com.example.km_movie_app.domain.model.Movie

interface MovieRepository {
    suspend fun getMovies(forceReload: Boolean):Resource<List<Movie>>
}