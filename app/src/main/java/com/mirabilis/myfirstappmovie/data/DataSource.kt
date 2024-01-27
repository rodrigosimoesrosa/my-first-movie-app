package com.mirabilis.myfirstappmovie.data

import com.mirabilis.myfirstappmovie.data.network.API
import com.mirabilis.myfirstappmovie.data.network.NetworkProvider
import com.mirabilis.myfirstappmovie.domain.entity.Genre
import com.mirabilis.myfirstappmovie.domain.entity.NewMovie

class DataSource {
    private val api: API = NetworkProvider.api()

    suspend fun getMovie(): NewMovie {
        return api.getMovie()
    }

    suspend fun getGenres(): List<Genre> {
        return api.getGenres().genres
    }
}