package com.dicoding.bangkit.android.jetpack.showcatalogueapp

import android.app.Application
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.di.Koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MyApp : Application() {
    override fun onCreate() {

        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(appModule)
        }
        super.onCreate()
    }
}