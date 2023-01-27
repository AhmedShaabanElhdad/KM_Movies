package com.example.km_movie_app.di

import com.example.km_movie_app.data.local.MoviesDao
import com.example.km_movie_app.data.local.MoviesDatasource
import com.example.km_movie_app.data.remote.mapper.MovieDBMapper
import com.example.km_movie_app.data.remote.mapper.MovieMapper
import com.example.km_movie_app.data.repository.MovieRepositoryImpl
import com.example.km_movie_app.domain.repository.MovieRepository
import com.example.km_movie_app.domain.usecase.GetMoviesUseCase
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module




fun initKoin(appDeclaration: KoinAppDeclaration = {}){
    startKoin {

        appDeclaration()

        modules(
            commonModule,
            networkModule,
            datasourceModule,
            mapperModule,
            repositoryModule,
            useCaseModule,
            platformModule()
        )

    }
}


// IOS
fun initKoin() = initKoin {

}

val commonModule = module {
//    single { ::Platform }
//    single { ::Greeting }
}

val datasourceModule = module {
    single<MoviesDatasource> { MoviesDao(get(),get()) }
    factory<DispatcherProvider> { DispatcherProviderImpl() }
}

val mapperModule = module {
    single { MovieMapper() }
    single { MovieDBMapper() }
}

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get(),get (),get()) }
}
val useCaseModule = module {
    factory { GetMoviesUseCase(get()) }
}