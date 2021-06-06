package com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.listui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.data.DataRepository
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.data.MovieHead
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.data.TvShowHead
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.di.Koin.appModule
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.utils.EspressoIdlingResource
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.utils.LiveDataTestUtil
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.utils.Resource
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class ListHomeViewModelTest : KoinTest {

    private val repoReal by inject<DataRepository>()
    private val dispatcher = TestCoroutineDispatcher()


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<Resource<Any>>

    @Mock
    private lateinit var observer2: Observer<Resource<Any>>

    private lateinit var listViewModel: ListHomeViewModel

    @Mock
    private lateinit var espresso: EspressoIdlingResource

    @Suppress("DEPRECATION")
    @Before
    fun setUp() {
        startKoin {
            androidLogger(Level.NONE)
            androidLogger()
//            androidContext()
            modules(appModule)
        }
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.initMocks(this)
        listViewModel = ListHomeViewModel(repoReal, espresso)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        stopKoin()
    }

    @Test
    fun getFilm() {

        val films: MovieHead = runBlocking { repoReal.getFilms() }
        val resources = Resource.success(data = films)
        listViewModel.getFilmku()
        val value = LiveDataTestUtil.getValue(listViewModel.films)
        Assert.assertNotNull(value)
        val data = value.data as MovieHead
        println(value)
        listViewModel.films.observeForever(observer)
        verify(observer).onChanged(resources)
        assertEquals(data.totalResults, films.totalResults)


    }

    @Test
    fun getTv() {
        val tvs: TvShowHead = runBlocking { repoReal.getTvs() }
        Assert.assertNotNull(tvs)

        val resources = Resource.success(data = tvs)
        listViewModel.getTvku()
        val value = LiveDataTestUtil.getValue(listViewModel.tvs)
        Assert.assertNotNull(value)
        val data = value.data as TvShowHead
        listViewModel.tvs.observeForever(observer2)
        verify(observer2).onChanged(resources)
        assertEquals(data.totalResults, tvs.totalResults)

    }
}