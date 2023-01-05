package com.example.km_movie_app.data.local

import com.example.km_movie_app.cache.MoviesDb
import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

