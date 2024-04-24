package com.mirabilis.myfirstappmovie.domain.entity.movies.genre

import com.google.gson.annotations.SerializedName

data class GetMovieByGenre (
    var page: Long? = null,
    var results: List<MovieByGenre>? = null,

    @SerializedName("total_pages")
    var totalPages: Long? = null,

    @SerializedName("total_results")
    var totalResults: Long? = null
)