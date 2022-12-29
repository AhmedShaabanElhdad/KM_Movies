package com.example.km_movie_app.data.remote.mapper

import com.example.km_movie_app.domain.core.Mapper
import com.example.km_movie_app.domain.model.Movie
import com.example.kmmovieapp.cache.MOVIE_TB

class MovieDBMapper:Mapper<List<MOVIE_TB>,List<Movie>> {

    override fun transfer(response: List<MOVIE_TB>): List<Movie> {
        return response.map { movieDto ->
            Movie(
                id = movieDto.id.toInt(),
                name = movieDto.name,
                overview = movieDto.overview,
                poster_url = "https://image.tmdb.org/t/p/w300".plus(movieDto.poster_url)
            )
        }
    }

}