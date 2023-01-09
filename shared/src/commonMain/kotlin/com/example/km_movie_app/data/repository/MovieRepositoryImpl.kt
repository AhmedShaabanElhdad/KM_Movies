package com.example.km_movie_app.data.repository

import com.example.km_movie_app.data.local.MoviesDatasource
import com.example.km_movie_app.data.remote.mapper.MovieMapper
import com.example.km_movie_app.data.remote.service.MovieApi
import com.example.km_movie_app.domain.core.Resource
import com.example.km_movie_app.domain.model.Movie
import com.example.km_movie_app.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val service: MovieApi,
    private val dao: MoviesDatasource,
    private val mapper: MovieMapper
) : MovieRepository {

    override suspend fun getMovies(forceReload: Boolean): Resource<List<Movie>> {
        return try {
            val cachedMovies = dao.getAllMovies()
            val result: Resource<List<Movie>> = if (cachedMovies.isNotEmpty() && !forceReload) {
                Resource.Success(cachedMovies)
            } else {
                val response = service.getMovies()
                response.takeIf { response.success }?.also { result ->
                        dao.deleteAllMovie().also {
                            dao.insertAll(result.results)
                        }
                }?.let{
                    Resource.Success(mapper.transfer(it.results))
                }
                    ?: Resource.Error(response.status_message)

            }
            result

        } catch (ex: Exception) {
            Resource.Error(ex.message ?: "Unknown Error")
        }
    }

}