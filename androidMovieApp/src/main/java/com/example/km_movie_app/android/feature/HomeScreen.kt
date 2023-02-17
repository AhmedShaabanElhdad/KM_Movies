package com.example.km_movie_app.android.feature

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.km_movie_app.android.feature.extension.toSnippit
import com.example.km_movie_app.android.state.Empty
import com.example.km_movie_app.android.state.Error
import com.example.km_movie_app.android.state.Loading
import com.example.km_movie_app.domain.model.Movie
import com.example.km_movie_app.presentation.feature.MovieContract
import com.example.km_movie_app.presentation.feature.MovieViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    viewModel: MovieViewModel,
    onNavigate: (String) -> Unit
) {

    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                MovieContract.MovieEffect.NavigateToDetails -> {

                }
                is MovieContract.MovieEffect.ShowError -> {
                    Toast.makeText(context, effect.msg, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        when (val result = state.state) {
            MovieContract.MovieState.Empty -> Empty(
                onCheckAgain = {
                    viewModel.setEvent(MovieContract.MovieEvent.GetMovies)
                }, msg = "there is no movies"
            )
            is MovieContract.MovieState.Error -> {
                Error(onTryAgain = {
                    viewModel.setEvent(MovieContract.MovieEvent.GetMovies)
                }, msg = result.msg)
            }
            MovieContract.MovieState.Idle -> Unit
            MovieContract.MovieState.Loading -> Loading()
            is MovieContract.MovieState.Success -> AllMoviesView(result.movies)
        }
    }
}

@Composable
fun AllMoviesView(movies: List<Movie>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        items(movies) { movie ->
            CharacterItem(
                movie = movie,
                onClick = { }
            )
        }
    }
}

@Composable
fun CharacterItem(movie: Movie, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Image(
            painter = rememberImagePainter(movie.poster_url),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 10.dp, start = 10.dp, bottom = 10.dp)
                .width(110.dp)
                .height(110.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = movie.name,
                modifier = Modifier
                    .fillMaxWidth(),
                fontFamily = FontFamily.Cursive,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = movie.overview.toSnippit(),
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }

    }
}
