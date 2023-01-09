package com.example.km_movie_app.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(
    val status_code: Int = 0,
    val status_message: String = "",
    val success: Boolean = true,
    val page: Int = 1,
    val results: List<MovieDTO> = emptyList(),
    val total_pages: Int = 1,
    val total_results: Int = 0
)