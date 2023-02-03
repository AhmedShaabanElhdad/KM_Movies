package com.example.km_movie_app.di

import com.example.km_movie_app.data.remote.service.MovieApi
import com.example.km_movie_app.data.remote.service.MovieApiImpl
import com.github.aakira.napier.Napier
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import org.koin.dsl.module



val networkModule = module {
    single { HttpEngineProvider().engine }
    single { provideHttpClient(get()) }
    single<MovieApi> { MovieApiImpl(get()) }
}

private fun provideHttpClient(engine: HttpClientEngine) = HttpClient(engine) {

    install(JsonFeature) {
        val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
        serializer = KotlinxSerializer(json)
    }

    install(Logging) {
        level = LogLevel.HEADERS
        logger = object : Logger {
            override fun log(message: String) {
                Napier.v(tag = "HTTP Client", message = message)
            }
        }
    }

//    install(JsonFeature) {
//        accept(ContentType("application", "vnd.api+json"))
//        val json = Json {
//            prettyPrint = true
//            isLenient = true
//            ignoreUnknownKeys = true
//        }
//        serializer = KotlinxSerializer(json)
//    }

//    install(HttpTimeout) {
//        val timeout = 10000L
//        connectTimeoutMillis = timeout
//        requestTimeoutMillis = timeout
//        socketTimeoutMillis = timeout
//    }

//    defaultRequest {
        //ParameterName("api_key", "some_api_key")
        // Content Type
//        if (method != HttpMethod.Get) contentType(ContentType.Application.Json)
//        accept(ContentType.Application.Json)
//    }

//        expectSuccess = true
//        install(ContentNegotiation) {
//            json()
//        }
//        install(Logging) {
//            logger = object : KtorLogger {
//                override fun log(message: String) {
//                    log.v { message }
//                }
//            }
//            level = LogLevel.INFO
//        }

}