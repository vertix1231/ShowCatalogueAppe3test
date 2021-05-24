package com.topanlabs.filmtopan.data

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.topanlabs.filmtopan.db.ShowtaimentDao
import com.topanlabs.filmtopan.db.ShowtaimentEntity
import com.topanlabs.filmtopan.network.TmApi

/**
 * Created by taufan-mft on 5/1/2021.
 */
class DataRepository(private val tmApi: TmApi, private val showtaimentDao: ShowtaimentDao) {
    suspend fun getFilms() = tmApi.getMovies()
    suspend fun getTvs() = tmApi.getTvs()
    suspend fun getFilmDetail(movieID: Int) = tmApi.getFilmDetail(movieID)
    suspend fun getTvDetail(tvID: Int) = tmApi.getTvDetail(tvID)
    suspend fun getFilmRating(movieID: Int) = tmApi.getFilmRating(movieID)
    suspend fun getTvRating(tvID: Int) = tmApi.getTvRating(tvID)
    fun allLikedArts(type: String): LiveData<PagedList<ShowtaimentEntity>> =
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