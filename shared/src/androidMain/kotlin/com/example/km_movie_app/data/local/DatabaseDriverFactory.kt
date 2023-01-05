package com.example.km_movie_app.data.local

import android.content.Context
import com.example.km_movie_app.cache.MoviesDb
import com.example.km_movie_app.domain.model.Movie
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(MoviesDb.Schema, context, "movies.db")
    }
}