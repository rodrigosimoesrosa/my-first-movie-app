package com.mirabilis.myfirstappmovie.view.genre

import androidx.compose.runtime.Immutable
import com.mirabilis.myfirstappmovie.domain.entity.Genre

@Immutable
data class GenresScreenState(
    val isLoading: Boolean = false,
    val genres: List<Genre> = emptyList(),
    val error: String? = null
)
