package com.example.km_movie_app.presentation.core.executor

/**
 * Created by Daniel Ávila Domingo on 6/10/21.
 **/
import com.example.km_movie_app.di.DispatcherProvider
import com.example.km_movie_app.domain.MainDispatcher
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.coroutines.CoroutineContext

abstract class MainIoExecutor : IExecutorScope, CoroutineScope, KoinComponent {

    private val mainDispatcher: MainDispatcher by inject()
    private val ioDispatcher: DispatcherProvider by inject()

    private val  job: CompletableJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + mainDispatcher.dispatcher

    override fun cancel() {
        job.cancel()
    }

    protected fun <T> collect(
        flow: Flow<T>, collect: (T) -> Unit
    ) {
        launch {
            flow
                .flowOn(ioDispatcher.io)
                .collect {
                    collect(it)
                }
        }
    }
}