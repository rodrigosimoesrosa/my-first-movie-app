package com.mirabilis.myfirstappmovie.data.database.favorite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteMoviesDao {
    @Query("SELECT * FROM FavoriteMovies")
    suspend fun getAll(): List<FavoriteMoviesDB>

    @Query("SELECT * FROM FavoriteMovies WHERE movieId IN (:moviesIds)")
    suspend fun loadAllByIds(moviesIds: IntArray): List<FavoriteMoviesDB>

    @Query("SELECT COUNT(*) FROM FavoriteMovies WHERE movieId = :movieId")
    suspend fun countOf(movieId: Int): Int

    @Insert
    suspend fun insert(vararg movies: FavoriteMoviesDB)

    @Query("DELETE FROM FavoriteMovies WHERE movieId = :movieId")
    suspend fun delete(movieId: Int)
}

