package com.dicoding.bangkit.android.jetpack.showcatalogueapp.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.db.ShowtaimentDao
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.db.ShowtaimentEntity
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.network.TmApi


class DataRepository(private val tmApi: TmApi, private val showtaimentDao: ShowtaimentDao) {
    suspend fun getFilms() = tmApi.getMovies()
    suspend fun getTvs() = tmApi.getTvs()
    suspend fun getFilmDetail(movieID: Int) = tmApi.getFilmDetail(movieID)
    suspend fun getTvDetail(tvID: Int) = tmApi.getTvDetail(tvID)
    suspend fun getFilmRating(movieID: Int) = tmApi.getFilmRating(movieID)
    suspend fun getTvRating(tvID: Int) = tmApi.getTvRating(tvID)
    @Suppress("DEPRECATION")
    fun allLikedAShowtaiment(type: String): LiveData<PagedList<ShowtaimentEntity>> =
        LivePagedListBuilder(showtaimentDao.getFavoriteList(type), 20).build()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(showtaimentEntity: ShowtaimentEntity) {
        showtaimentDao.insert(showtaimentEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(showtaimentEntity: ShowtaimentEntity) {
        showtaimentDao.delete(showtaimentEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun searchArt(id: Int): Int {
        return showtaimentDao.searchArt(id)
    }
}