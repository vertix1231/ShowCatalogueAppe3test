package com.topanlabs.filmtopan.data


import com.google.gson.annotations.SerializedName

data class TvShowHead(
    @SerializedName("page")
    val page: Int,
    @SerializedName("movieResultResponses")
    val tvshowResults: List<TvshowResultResponses>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)