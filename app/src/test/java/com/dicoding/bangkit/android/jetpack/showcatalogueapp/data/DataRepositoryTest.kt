package com.dicoding.bangkit.android.jetpack.showcatalogueapp.data

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.db.ShowtaimentDao
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.db.ShowtaimentEntity
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext
import org.koin.test.KoinTest
import org.koin.test.get
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
class DataRepositoryTest:KoinTest {

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
        GlobalContext.stopKoin()
    }

    @Test
    fun allLikedArts() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, ShowtaimentEntity>
        Mockito.`when`(dao.getFavoriteList("tv")).thenReturn(dataSourceFactory)
        repository.allLikedArts("tv")
        verify(dao).getFavoriteList("tv")
    }

}