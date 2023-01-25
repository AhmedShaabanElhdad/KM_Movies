package com.example.km_movie_app.domain.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

/**
 * Base Use Case class
 */
abstract class BaseUseCase<Model, Params> {

    abstract suspend fun buildRequest(params: Params?): Resource<Model>

    fun execute(params: Params?): Flow<Resource<Model>> {
        return try {
            flow { emit(buildRequest(params)) }
        } catch (exception: Exception) {
            flow { emit(Resource.Error(exception.message ?: "Error")) }
        }
    }
}

abstract class BaseUseFlowCase<Model, Params> {

    abstract fun buildRequest(params: Params?): Flow<Resource<Model>>

    fun execute(params: Params?): Flow<Resource<Model>> {
        return try {
            buildRequest(params)
        } catch (exception: Exception) {
            flow { emit(Resource.Error(exception.message ?: "Error")) }
        }
    }
}