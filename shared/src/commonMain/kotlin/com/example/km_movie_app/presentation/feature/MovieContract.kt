package com.example.km_movie_app.presentation.feature

import com.example.km_movie_app.domain.model.Movie
import com.example.km_movie_app.presentation.core.mvi.UiEffect
import com.example.km_movie_app.presentation.core.mvi.UiEvent
import com.example.km_movie_app.presentation.core.mvi.UiState

sealed class MovieContract {

    sealed class MovieState<out T> {
        object Idle : MovieState<Nothing>()
        object Loading : MovieState<Nothing>()
        object Empty : MovieState<Nothing>()
        class Error(val msg: String) : MovieState<Nothing>()
        class Success<T>(val movies: T) : MovieState<T>()
    }

    sealed class MovieEvent: UiEvent {
     object GetMovies :MovieEvent()
    }

    sealed class MovieEffect: UiEffect {
        class ShowError(val msg: String):MovieEffect()
        object NavigateToDetails:MovieEffect()
    }

    data class State(
        val state:MovieState<List<Movie>>
    ): UiState
}
