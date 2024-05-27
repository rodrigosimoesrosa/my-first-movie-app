package com.mirabilis.myfirstappmovie.data

import com.mirabilis.myfirstappmovie.data.database.Database
import com.mirabilis.myfirstappmovie.data.database.favorite.FavoriteMoviesDB

class OldLocalFavoriteMoviesDataSource {

    suspend fun favorite(movieId: Int) {
        Database.getFavoriteMoviesDao().insert(FavoriteMoviesDB(movieId = movieId))
    }

    suspend fun unFavorite(movieId: Int) {
        Database.getFavoriteMoviesDao().delete(movieId)
    }

    suspend fun isFavorite(movieId: Int): Boolean = Database.getFavoriteMoviesDao().countOf(movieId) > 0
}
