package com.mirabilis.myfirstappmovie.view.genre

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirabilis.myfirstappmovie.data.DataSource
import com.mirabilis.myfirstappmovie.domain.entity.Genre
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GenresViewModel : ViewModel() {

    private val dataSource = DataSource()

    private val _state = MutableStateFlow(GenresScreenState())
    val state: StateFlow<GenresScreenState> = _state
    
    fun loadGenres() {
        viewModelScope.launch {
            try {
                updateState(state.value.copy(isLoading = true))
                val genres: List<Genre> = dataSource.getGenres()
                updateState(state.value.copy(isLoading = false, genres = genres))
            } catch (ex: Exception) {
                updateState(state.value.copy(isLoading = false, error = ex.message!!))
            }
        }
    }

    fun loadFakeGenres() {
        viewModelScope.launch {
            try {
                updateState(state.value.copy(isLoading = true))
                delay(2000)
                updateState(state.value.copy(
                    isLoading = false,
                    genres = listOf
                        (
                            Genre(1, "Horror"),
                            Genre(2, "Suspense")
                        )
                    )
                )
            } catch (ex: Exception) {
                updateState(state.value.copy(isLoading = false, error = ex.message!!))
            }
        }
    }

    private fun updateState(state: GenresScreenState) {
        Log.v("STATE", "===> $state")
        viewModelScope.launch { _state.tryEmit(state) }
    }
}