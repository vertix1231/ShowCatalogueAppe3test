package com.topanlabs.filmtopan.data


import com.google.gson.annotations.SerializedName

data class MovieHead(
    @SerializedName("page")
    val page: Int,
    @SerializedName("movieResultResponses")
    val movieResultResponses: List<MovieResultResponses>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)