package com.mirabilis.myfirstappmovie.data

import com.mirabilis.myfirstappmovie.data.network.API
import com.mirabilis.myfirstappmovie.data.network.NetworkProvider
import com.mirabilis.myfirstappmovie.domain.entity.Genre
import com.mirabilis.myfirstappmovie.domain.entity.GetGenres
import com.mirabilis.myfirstappmovie.domain.entity.NewMovie
import retrofit2.Call
import retrofit2.Callback

/**
 * Fonte de dados
 */
class OldDataSource {

    private val api: API = NetworkProvider.api()

    fun getMovie(): Call<NewMovie> {
        return api.getOldMovie()
    }

    fun getGenres(): Call<GetGenres> {
        return api.getOldGenres()
    }
}