package com.example.km_movie_app.domain.usecase

import com.example.km_movie_app.domain.core.BaseUseCase
import com.example.km_movie_app.domain.core.Resource
import com.example.km_movie_app.domain.model.Movie
import com.example.km_movie_app.domain.repository.MovieRepository

class GetMoviesUseCase(
    private val repository: MovieRepository,
):BaseUseCase<List<Movie>,Boolean> (){
    override suspend fun buildRequest(params: Boolean?): Resource<List<Movie>> {
        return repository.getMovies(params!!)
    }
}