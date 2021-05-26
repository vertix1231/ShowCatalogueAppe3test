package com.dicoding.bangkit.android.jetpack.showcatalogueapp.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.db.ShowtaimentDao
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.db.ShowtaimentEntity
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [24])
class DataRepositoryTest : KoinTest{

    private lateinit var repository: DataRepository
    private val dispatcher = TestCoroutineDispatcher()



    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dao = Mockito.mock(ShowtaimentDao::class.java)

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        repository = DataRepository(get(), dao)
        MockitoAnnotations.initMocks(this)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        stopKoin()
    }

    @Test
    fun allLikedArts() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ShowtaimentEntity>
        Mockito.`when`(dao.getFavoriteList("tv")).thenReturn(dataSourceFactory)
        repository.allLikedArts("tv")
        verify(dao).getFavoriteList("tv")
    }

    @Test
    fun getFilms() {
        runBlocking {
            val films = repository.getFilms()
            assertNotNull(films)
            val data = films.results[0].originalTitle
            println(data)
        }
    }

    @Test
    fun getTvs() {
        runBlocking {
            val tvs = repository.getTvs()
            assertNotNull(tvs)
            val data = tvs.results[0].originalName
            println(data)
        }
    }

    @Test
    fun getFilmDetail() {
        runBlocking {
            val films = repository.getFilms()
            assertNotNull(films)
            val data = films.results[0]
            val filmId = data.id
            val filmDetail = repository.getFilmDetail(filmId)
            assertEquals(data.originalTitle, filmDetail.originalTitle)
        }
    }

    @Test
    fun getTvDetail() {
        runBlocking {
            val tvs = repository.getTvs()
            assertNotNull(tvs)
            val data = tvs.results[0]
            val tvId = data.id
            val tvDetail = repository.getTvDetail(tvId)
            assertEquals(data.originalName, tvDetail.originalName)
        }
    }

    @Test
    fun getFilmRating() {
        runBlocking {
            val films = repository.getFilms()
            assertNotNull(films)
            val data = films.results[0]
            val filmId = data.id
            val rating = repository.getFilmRating(filmId)
            assertNotNull(rating)
            assertEquals(data.id, rating.id)
        }
    }

    @Test
    fun getTvRating() {
        runBlocking {
            val tvs = repository.getTvs()
            assertNotNull(tvs)
            val data = tvs.results[0]
            val tvId = data.id
            val rating = repository.getTvRating(tvId)
            assertNotNull(rating)
            assertEquals(rating.id, data.id)
        }
    }

    @Test
    fun testInsert() {
        val artEntity =
            ShowtaimentEntity(id = 1, title = "raisa", photo = "tasya", type = "tv", year = "2020")
        runBlocking {
            repository.insert(artEntity)
            verify(dao).insert(artEntity)
        }
    }

    @Test
    fun testDelete() {
        val artEntity =
            ShowtaimentEntity(id = 1, title = "raisa", photo = "tasya", type = "tv", year = "2020")
        runBlocking {
            repository.delete(artEntity)
            verify(dao).delete(artEntity)
        }
    }

}