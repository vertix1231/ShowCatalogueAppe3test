package com.dicoding.bangkit.android.jetpack.showcatalogueapp.data


import com.google.gson.annotations.SerializedName

data class MovieHead(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieResultResponses>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)