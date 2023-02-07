package com.example.km_movie_app.presentation.feature

import com.example.km_movie_app.domain.core.Resource
import com.example.km_movie_app.domain.usecase.GetMoviesUseCase
import com.example.km_movie_app.presentation.core.mvi.BaseViewModel
import com.example.km_movie_app.presentation.feature.MovieContract.*

class MovieViewModel(
    private val moviesUseCase: GetMoviesUseCase
) : BaseViewModel<MovieEvent, State, MovieEffect>() {


    override fun createInitialState() = State(MovieState.Idle)

    init {
        setEvent(MovieEvent.GetMovies)
    }

    override fun handleEvent(event: MovieEvent) {
        when (event) {
            MovieEvent.GetMovies -> getMovies()
        }
    }


    private fun getMovies(forceReload:Boolean = false) {
        setState { copy(state = MovieState.Loading) }
        collect(moviesUseCase.execute(forceReload)) {
            when (it) {
                is Resource.Error -> {
                    setEffect { MovieEffect.ShowError(it.message) }
                    setState { copy(state = MovieState.Error(it.message)) }
                }
                is Resource.Success -> {
                    setState { copy(state = MovieState.Success(it.result)) }
                }
            }
        }
    }
}