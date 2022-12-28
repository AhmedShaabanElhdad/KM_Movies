package com.example.km_movie_app.data.local

import com.example.km_movie_app.data.remote.response.MovieDTO
import com.example.km_movie_app.domain.model.Movie

interface MoviesDatasource {

    fun insertMovie(id: Long?, name: String, overview: String, posterUrl: String)

    fun deleteMovie(id: Long)

    fun deleteAllMovie()

    suspend fun getAllMovies(): List<Movie>

    suspend fun insertAll(result: List<MovieDTO>)

}