package com.mirabilis.myfirstappmovie.oldway.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirabilis.myfirstappmovie.data.OldLocalFavoriteMoviesDataSource
import kotlinx.coroutines.launch

class OldMovieViewModel: ViewModel() {

    private val oldLocalFavoriteMoviesDataSource = OldLocalFavoriteMoviesDataSource()

    private val _favoritado: MutableLiveData<Boolean> = MutableLiveData()
    fun isFavoritado(): LiveData<Boolean> = _favoritado

    fun check(movieId: Int?) {
        if (movieId == null) return

        viewModelScope.launch {
            val result = oldLocalFavoriteMoviesDataSource.isFavorite(movieId)
            _favoritado.postValue(result)
        }
    }

    fun favoritar(movieId: Int) {
        viewModelScope.launch {
            oldLocalFavoriteMoviesDataSource.favorite(movieId)
            _favoritado.postValue(true)
        }
    }

    fun desfavoritar(movieId: Int) {
        viewModelScope.launch {
            oldLocalFavoriteMoviesDataSource.unFavorite(movieId)
            _favoritado.postValue(false)
        }
    }

    fun executeFavorite(movieId: Int?) {
        if (movieId == null) return

        if (isFavoritado().value == true) {
            desfavoritar(movieId)
            return
        }

        favoritar(movieId)
    }
}