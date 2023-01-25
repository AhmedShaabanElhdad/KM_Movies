package com.example.km_movie_app.domain.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Base Use Case class
 */
abstract class BaseFlowUseCase<Model, Params> {

    abstract suspend fun buildRequest(params: Params?): Flow<Resource<Model>>

    suspend fun execute(params: Params?): Flow<Resource<Model>> {
        return try {
            buildRequest(params)
        } catch (exception: Exception) {
            flow { emit(Resource.Error(exception.message ?:"Error")) }
        }
    }
}