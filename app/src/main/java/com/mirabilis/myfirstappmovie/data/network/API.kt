package com.mirabilis.myfirstappmovie.data.network

import com.mirabilis.myfirstappmovie.domain.entity.GetGenres
import com.mirabilis.myfirstappmovie.domain.entity.NewMovie
import com.mirabilis.myfirstappmovie.domain.entity.movies.genre.GetMovieByGenre
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interfaces fornecem funções/ações/metódos
 */
interface API {

    @GET("movie/157336?language=pt-BR")
    suspend fun getMovie(): NewMovie

    @GET("genre/movie/list?language=pt-BR")
    suspend fun getGenres(): GetGenres

    @GET("movie/{movieId}?language=pt-BR")
    fun getOldMovie(@Path("movieId") movieId: Long): Call<NewMovie>

    @GET("discover/movie?language=pt-BR")
    fun getOldMoviesByGenre(
        @Query("with_genres") id: Long,
        @Query("page") page: Int = 1
    ): Call<GetMovieByGenre>

    @GET("genre/movie/list?language=pt-BR")
    fun getOldGenres(): Call<GetGenres>
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