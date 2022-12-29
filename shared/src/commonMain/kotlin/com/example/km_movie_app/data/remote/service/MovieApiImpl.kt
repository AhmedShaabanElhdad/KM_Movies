package com.example.km_movie_app.data.remote.service

import com.example.km_movie_app.data.remote.core.EndPoint
import com.example.km_movie_app.data.remote.response.MovieResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class MovieApiImpl(private val httpClient: HttpClient) : MovieApi {

    override suspend fun getMovies(): MovieResponse {
         return try {
            httpClient.get {
                headers { "Accept" to "application/json" }
                url(EndPoint.GetAllMovies.url)
            }
//             httpClient.get<MovieResponse> { apiUrl("/popular?api_key=bb8d34f0a3094f98177faa4c1945c778") }
        }catch (e:Exception){
            MovieResponse(-1,e.message?:"error",success = false)
        }
    }


    private fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom("https://api.themoviedb.org/3/movie")
            encodedPath = path
        }
    }
}