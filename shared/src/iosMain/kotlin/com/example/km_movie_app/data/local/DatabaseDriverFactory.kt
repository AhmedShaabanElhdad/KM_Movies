package com.example.km_movie_app.data.local

import co.touchlab.sqliter.DatabaseConfiguration
import com.example.km_movie_app.cache.MoviesDb
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.drivers.native.wrapConnection

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(MoviesDb.Schema, "movies.db")
    }

//    private var index = 0
//
//    actual fun createInMemorySqlDriver(): SqlDriver {
//        index++
//        val schema = MoviesDb.Schema
//        return NativeSqliteDriver(
//            DatabaseConfiguration(
//                name = "movies-$index.db",
//                version = schema.version,
//                create = { connection ->
//                    wrapConnection(connection) { schema.create(it) }
//                },
//                upgrade = { connection, oldVersion, newVersion ->
//                    wrapConnection(connection) {
//                        schema.migrate(it, oldVersion, newVersion)
//                    }
//                },
//                inMemory = true
//            )
//        )
//    }
}