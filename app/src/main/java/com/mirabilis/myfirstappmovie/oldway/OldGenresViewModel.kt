package com.mirabilis.myfirstappmovie.oldway

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mirabilis.myfirstappmovie.data.OldDataSource
import com.mirabilis.myfirstappmovie.domain.entity.Genre
import com.mirabilis.myfirstappmovie.domain.entity.GetGenres
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * O view model é responsavel por gerenciar tarefas que não são de responsabilidade da TELA (ACTIVITY/FRAGMENT)
 */
class OldGenresViewModel : ViewModel() {

    /**
     * Fonte de dados
     */
    private val dataSource: OldDataSource = OldDataSource()

    /**
     * Cria objetos observáveis, que retornaram os valores para quem os observa
     * (ACTIVITY nesse caso está observando esses 3 objetos)
     */
    private val _genres: MutableLiveData<List<Genre>> = MutableLiveData(listOf())
    fun getGenres(): LiveData<List<Genre>> = _genres

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    fun isLoading(): LiveData<Boolean> = _loading

    private val _error: MutableLiveData<Throwable?> = MutableLiveData()
    fun hasError(): LiveData<Throwable?> = _error

    fun clearError() { _error.postValue(null) }

    /**
     * Inicia o view model assim que ele for criado na ACTIVITY
     */
    init {
        loadGenres()
    }

    /**
     * Função que carrega da fonte de dados os generos e insere os valores recebidos da
     * internet dentro os objetos observaveis
     */
    fun loadGenres() {
        _loading.postValue(true)

        dataSource.getGenres().enqueue(object : Callback<GetGenres> {
            override fun onResponse(
                call: Call<GetGenres>,
                response: Response<GetGenres>
            ) {
                _loading.postValue(false)

                if (response.isSuccessful) {
                    _genres.postValue(response.body()?.genres)
                    return
                }

                _genres.postValue(listOf())
            }

            override fun onFailure(
                call: Call<GetGenres>,
                throwable: Throwable
            ) {
                _loading.postValue(false)
                _genres.postValue(listOf())
                _error.postValue(throwable)
            }
        })
    }
}