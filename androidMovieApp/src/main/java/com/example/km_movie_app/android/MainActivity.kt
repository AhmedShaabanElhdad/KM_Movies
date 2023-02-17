package com.example.km_movie_app.android

//import com.example.km_movie_app.android.di.GreetPresenter
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.km_movie_app.android.feature.HomeScreen
import com.example.km_movie_app.android.theme.KMMAppTheme
import com.example.km_movie_app.presentation.feature.MovieViewModel
import org.koin.android.ext.android.inject



class MainActivity : AppCompatActivity() {

    private val movieViewModel:MovieViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KMMAppTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home_screen" ){
                        composable(route = "home_screen"){
                            HomeScreen(onNavigate = navController::navigate, viewModel = movieViewModel)
                        }
                    }
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        movieViewModel.cancel()
    }

}
