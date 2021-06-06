package com.dicoding.bangkit.android.jetpack.showcatalogueapp

import android.app.Application
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.di.Koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger(Level.NONE)
            androidLogger()
            androidContext(this@MyApp)
            modules(appModule)
        }

    }
}