package com.mirabilis.myfirstappmovie.domain.entity.movies.genre

data class MovieByGenre (
    var adult: Boolean? = null,
    var backdropPath: String? = null,
    var genreIDS: List<Long>? = null,
    var id: Long? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var releaseDate: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var voteAverage: Double? = null,
    var voteCount: Long? = null
)