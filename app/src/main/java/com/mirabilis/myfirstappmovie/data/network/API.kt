package com.mirabilis.myfirstappmovie.data.network

import com.mirabilis.myfirstappmovie.domain.entity.GetGenres
import com.mirabilis.myfirstappmovie.domain.entity.NewMovie
import retrofit2.http.GET

/**
 * Interfaces fornecem funções/ações/metódos
 */
interface API {

    @GET("movie/157336?language=pt-BR")
    suspend fun getMovie(): NewMovie

    @GET("genre/movie/list?language=pt-BR")
    suspend fun getGenres(): GetGenres

}

/*class ImplementacaoDaAPI () : API {
    override suspend fun getMovie(): NewMovie {
        vai na internet
        pega o filme
        converte o json para NewMovie
        retorna NewMovie
    }

    override suspend fun getGenres(): GetGenres {
        vai na internet
        pega o json que tem uma lista de generos
        converto o json para GetGenres
        retorna GetGenres
    }
}*/