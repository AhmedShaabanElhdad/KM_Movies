package com.example.km_movie_app.data.remote.mapper

import com.example.km_movie_app.data.remote.response.MovieDTO
import com.example.km_movie_app.domain.core.Mapper
import com.example.km_movie_app.domain.model.Movie

class MovieMapper:Mapper<List<MovieDTO>,List<Movie>> {

    override fun transfer(response: List<MovieDTO>): List<Movie> {
        return response.map { movieDto ->
            Movie(
                id = movieDto.id,
                name = movieDto.original_title,
                overview = movieDto.overview,
                poster_url = "https://image.tmdb.org/t/p/w300".plus(movieDto.poster_path)
            )
        }
    }

}