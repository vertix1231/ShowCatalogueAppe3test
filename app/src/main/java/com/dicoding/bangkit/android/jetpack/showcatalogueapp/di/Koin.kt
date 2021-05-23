package com.topanlabs.filmtopan.di

import com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.detail.DetailViewModel
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.listui.ListViewModel
import com.topanlabs.filmtopan.data.DataRepository
import com.topanlabs.filmtopan.db.ArtRoomDatabase
import com.topanlabs.filmtopan.network.RetroBuilder
import com.topanlabs.filmtopan.utils.EspressoIdlingResource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object Koin {
    val appModule = module {

        single<DataRepository> { DataRepository(get(), get()) }
        single { RetroBuilder.tmApi }
        single { EspressoIdlingResource() }
        single { ArtRoomDatabase.getDatabase(get()).artDao() }
        viewModel { ListViewModel(get(), get()) }
        viewModel { DetailViewModel(get(), get()) }
    }


}