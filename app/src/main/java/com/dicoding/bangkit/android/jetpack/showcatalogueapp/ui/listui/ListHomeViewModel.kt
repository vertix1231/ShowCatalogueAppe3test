package com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.listui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.data.DataRepository
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.utils.EspressoIdlingResource
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.utils.Resource
import kotlinx.coroutines.launch


class ListHomeViewModel(private val repository: DataRepository, private val espresso: EspressoIdlingResource) : ViewModel() {

    val films = MutableLiveData<Resource<Any>>()
    val tvs = MutableLiveData<Resource<Any>>()


    fun getFilmku() {
        espresso.increment()
        viewModelScope.launch {
            try {
                films.postValue(Resource.success(data = repository.getFilms()))
            } catch (exception: Exception) {
                films.postValue(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!"
                    )
                )
            }
            espresso.decrement()
        }

    }


    fun getTvku() {
        espresso.increment()
        viewModelScope.launch {
            try {
                tvs.postValue(Resource.success(data = repository.getTvs()))
            } catch (exception: Exception) {
                tvs.postValue(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!"
                    )
                )
            }
            espresso.decrement()
        }
    }

}