package com.example.km_movie_app.android.feature.extension

fun String.toSnippit(): String {
    return this.takeIf { it.length> 60 }?.let {
        it.substring(0,59).plus("...")
    } ?: this
}