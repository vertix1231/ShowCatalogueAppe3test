package com.dicoding.bangkit.android.jetpack.showcatalogueapp

import android.app.Application
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.data.DataRepository
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.db.ShowtaimentDatabase
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.network.RetroBuilder
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.detail.DetailViewModel
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.listui.ListHomeViewModel
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.utils.EspressoIdlingResource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.mockito.Mockito.mock

object KointTestModule {
    private val mockedAndroidContext = mock(Application::class.java)

    val appModuleTest = module {

        single { DataRepository(get(), get()) }
        single { RetroBuilder.tmApi }
        single { EspressoIdlingResource() }
        single { ShowtaimentDatabase.getDatabase(mockedAndroidContext).showtaimentDao() }
        viewModel { ListHomeViewModel(get(), get()) }
        viewModel { DetailViewModel(get(), get()) }
    }
}