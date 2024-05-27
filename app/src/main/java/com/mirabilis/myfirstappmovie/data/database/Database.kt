package com.mirabilis.myfirstappmovie.data.database

import android.content.Context
import androidx.room.Room
import com.mirabilis.myfirstappmovie.data.database.favorite.FavoriteMoviesDao

class Database {
    companion object {
        private var db: AppDatabase? = null

        fun newDatabase(applicationContext: Context) {
            db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "my-first-movie-app"
            ).build()
        }

        fun getFavoriteMoviesDao(): FavoriteMoviesDao = db?.favoriteDao() ?: throw Exception("Banco de dados não inicializado!")
    }
}
