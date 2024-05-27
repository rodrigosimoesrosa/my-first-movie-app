package com.mirabilis.myfirstappmovie.data.database.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteMovies")
data class FavoriteMoviesDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val movieId: Int
)
