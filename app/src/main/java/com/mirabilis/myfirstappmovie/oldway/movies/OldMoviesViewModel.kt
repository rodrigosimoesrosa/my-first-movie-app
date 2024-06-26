package com.mirabilis.myfirstappmovie.oldway.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mirabilis.myfirstappmovie.data.OldRemoteDataSource
import com.mirabilis.myfirstappmovie.domain.entity.Movie
import com.mirabilis.myfirstappmovie.domain.entity.movies.genre.GetMovieByGenre
import com.mirabilis.myfirstappmovie.domain.entity.movies.genre.MovieByGenre
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * O view model é responsavel por gerenciar tarefas que não são de responsabilidade da TELA (ACTIVITY/FRAGMENT)
 */
class OldMoviesViewModel: ViewModel() {


    /**
     * Fonte de dados
     */

    private val dataSource: OldRemoteDataSource = OldRemoteDataSource()


    /**
     * Cria objetos observáveis, que retornaram os valores para quem os observa
     * (ACTIVITY nesse caso está observando esses 3 objetos)
     */

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData(listOf())
    fun getMovies(genreId: Long): LiveData<List<Movie>> {
        loadMovies(genreId)
        return _movies
    }

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    fun isLoading(): LiveData<Boolean> = _loading

    private val _error: MutableLiveData<Throwable?> = MutableLiveData()
    fun hasError(): LiveData<Throwable?> = _error

    fun clearError() { _error.postValue(null) }

    /**
     * Funcao que carrega da fonte de dados os filmes e insere os valores recebidos da
     * internet dentro os objetos observaveis
     */

    fun loadMovies(genreId: Long) {
        _loading.postValue(true)

        dataSource.getMoviesByGenre(genreId).enqueue(object : Callback<GetMovieByGenre> {
            override fun onResponse(call: Call<GetMovieByGenre>, response: Response<GetMovieByGenre>) {
                _loading.postValue(false)
                if(response.isSuccessful){
                    val moviesByGenre: GetMovieByGenre? = response.body()
                    val results: List<MovieByGenre>? = moviesByGenre?.results
                    Log.d("APP", "results $results")
                    val values: List<Movie>? = results?.map {
                        Movie(
                            id = it.id?.toInt() ?: 0,
                            name = it.title ?: "",
                            diretor = "",
                            sinopse = it.overview ?: "",
                            url = it.posterPath ?: "",
                            popularity = it.popularity?.toDouble() ?: 0.0,
                            releaseDate = it.releaseDate ?:""

                        )
                    }

                    _movies.postValue(values ?: arrayListOf())
                    return
                }
                _movies.postValue(listOf())
            }

            override fun onFailure(call: Call<GetMovieByGenre>, t: Throwable) {
                _loading.postValue(false)
                _movies.postValue(listOf())

            }

        })

    }

}