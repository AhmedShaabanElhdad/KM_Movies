package com.example.km_movie_app.domain.model

import kotlinx.serialization.Serializable

data class Movie(
    val id: Int,
    val name: String,
    val overview: String,
    val poster_url: String
)