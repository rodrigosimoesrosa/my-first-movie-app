package com.mirabilis.myfirstappmovie.domain.entity.movies.genre

data class GetMovieByGenre (
    var page: Long? = null,
    var results: List<MovieByGenre>? = null,
    var totalPages: Long? = null,
    var totalResults: Long? = null
)