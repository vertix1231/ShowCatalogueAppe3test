package com.dicoding.bangkit.android.jetpack.showcatalogueapp.network

import com.dicoding.bangkit.android.jetpack.showcatalogueapp.data.*
import retrofit2.http.GET
import retrofit2.http.Path


interface TmApi {
    @GET("trending/movie/day?api_key=7f85d423ec1dba1aab33327dfb3fd290")
    suspend fun getMovies(): MovieHead

    @GET("trending/tv/day?api_key=7f85d423ec1dba1aab33327dfb3fd290")
    suspend fun getTvs(): TvShowHead

    @GET("https://api.themoviedb.org/3/movie/{movieID}?api_key=7f85d423ec1dba1aab33327dfb3fd290&language=en-US")
    suspend fun getFilmDetail(@Path("movieID") movieID: Int): MovieDetailData

    @GET("https://api.themoviedb.org/3/tv/{tvID}?api_key=7f85d423ec1dba1aab33327dfb3fd290&language=en-US")
    suspend fun getTvDetail(@Path("tvID") tvID: Int): TvDetailData

    @GET("https://api.themoviedb.org/3/tv/{tvID}/content_ratings?api_key=7f85d423ec1dba1aab33327dfb3fd290")
    suspend fun getTvRating(@Path("tvID") tvID: Int): RatingTvshowData

    @GET("https://api.themoviedb.org/3/movie/{movieID}/release_dates?api_key=7f85d423ec1dba1aab33327dfb3fd290&language=en-US")
    suspend fun getFilmRating(@Path("movieID") movieID: Int): RatingMovieData


}