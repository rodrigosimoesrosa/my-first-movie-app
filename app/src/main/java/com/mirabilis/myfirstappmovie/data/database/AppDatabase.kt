package com.mirabilis.myfirstappmovie.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mirabilis.myfirstappmovie.data.database.favorite.FavoriteMoviesDB
import com.mirabilis.myfirstappmovie.data.database.favorite.FavoriteMoviesDao

@Database(entities = [FavoriteMoviesDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteMoviesDao
}
