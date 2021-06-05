package com.dicoding.bangkit.android.jetpack.showcatalogueapp.di

import com.dicoding.bangkit.android.jetpack.showcatalogueapp.data.DataRepository
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.db.ShowtaimentDatabase
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.network.RetroBuilder
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.detail.DetailViewModel
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.listui.ListHomeViewModel
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.utils.EspressoIdlingResource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


object Koin {
    val appModule = module {

        single { DataRepository(get(), get()) }
        single { RetroBuilder.tmApi }
        single { EspressoIdlingResource() }
        single { ShowtaimentDatabase.getDatabase(get()).showtaimentDao() }
        viewModel { ListHomeViewModel(get(), get()) }
        viewModel { DetailViewModel(get(), get()) }
    }


}