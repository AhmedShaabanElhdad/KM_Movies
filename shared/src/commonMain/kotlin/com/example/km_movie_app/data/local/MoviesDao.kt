package com.example.km_movie_app.data.local

import com.example.km_movie_app.cache.MoviesDb
import com.example.km_movie_app.data.remote.mapper.MovieDBMapper
import com.example.km_movie_app.data.remote.mapper.MovieMapper
import com.example.km_movie_app.data.remote.response.MovieDTO
import com.example.km_movie_app.data.remote.response.MovieResponse
import com.example.km_movie_app.domain.model.Movie


class MoviesDao(
    databaseDriverFactory: DatabaseDriverFactory,
    private val mapper: MovieDBMapper
):MoviesDatasource {
    private val database = MoviesDb(databaseDriverFactory.createDriver())
    private val dbQuery = database.moviesTableQueries

    override fun insertMovie(id: Long?, name: String, overview: String, posterUrl: String) {
        dbQuery.saveMovie(id = id, name = name, overview = overview, poster_url = posterUrl)
    }

    override fun deleteMovie(id: Long) {
        dbQuery.deleteMovie(id)
    }
    override fun deleteAllMovie() {
        dbQuery.deleteAllMovie()
    }

    override suspend fun getAllMovies(): List<Movie> {
//        return LinkedHashSet(dbQuery.selectAll().executeAsList()).toList().map {
        return dbQuery.selectAll().executeAsList().run {
            mapper.transfer(this)
        }
    }

    override suspend fun insertAll(result: List<MovieDTO>) {
        result.forEach {
            insertMovie(
                id = it.id.toLong(),
                name = it.original_title,
                overview = it.overview,
                posterUrl = it.poster_path
            )
        }

    }


}