package com.dicoding.bangkit.android.jetpack.showcatalogueapp.network

import com.dicoding.bangkit.android.jetpack.showcatalogueapp.BuildConfig
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.data.*
import retrofit2.http.GET
import retrofit2.http.Path


interface TmApi {
    @GET("trending/movie/day?api_key=${BuildConfig.TMDB_API_KEY}")
    suspend fun getMovies(): MovieHead

    @GET("trending/tv/day?api_key=${BuildConfig.TMDB_API_KEY}")
    suspend fun getTvs(): TvShowHead

    @GET("https://api.themoviedb.org/3/movie/{movieID}?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    suspend fun getFilmDetail(@Path("movieID") movieID: Int): MovieDetailData

    @GET("https://api.themoviedb.org/3/tv/{tvID}?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    suspend fun getTvDetail(@Path("tvID") tvID: Int): TvDetailData

    @GET("https://api.themoviedb.org/3/tv/{tvID}/content_ratings?api_key=${BuildConfig.TMDB_API_KEY}")
    suspend fun getTvRating(@Path("tvID") tvID: Int): RatingTvshowData

    @GET("https://api.themoviedb.org/3/movie/{movieID}/release_dates?api_key=${BuildConfig.TMDB_API_KEY}&language=en-US")
    suspend fun getFilmRating(@Path("movieID") movieID: Int): RatingMovieData


}