package com.topanlabs.filmtopan.utils

import androidx.test.espresso.idling.CountingIdlingResource


class EspressoIdlingResource {
    private val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        idlingResource.increment()
    }

    fun decrement() {
        idlingResource.decrement()
    }
}