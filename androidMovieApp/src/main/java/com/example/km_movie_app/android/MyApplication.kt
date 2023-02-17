package com.example.km_movie_app.android

import android.app.Application
import com.example.km_movie_app.android.di.viewModelModule
import com.example.km_movie_app.di.initKoin
import org.koin.android.ext.koin.androidContext

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin{
            androidContext(this@MyApplication)
//            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            modules(
                viewModelModule,
//                androidModule
            )
        }
    }
}