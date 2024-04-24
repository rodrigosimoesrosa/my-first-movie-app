package com.mirabilis.myfirstappmovie.oldway.movies

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.mirabilis.myfirstappmovie.databinding.OldMovieItemBinding
import com.mirabilis.myfirstappmovie.domain.entity.Movie
import com.mirabilis.myfirstappmovie.domain.entity.movies.genre.MovieByGenre

/**
 * O viewHolder é a classe onde se faz o binding entre o código (Kotlin/Java) e o xml
 */
class MoviesViewHolder(
    private val binding: OldMovieItemBinding,
    onClick: (movie: Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var movie: Movie

    init {
        binding.root.setOnClickListener {
            Log.d("APP", "$movie")
            onClick(movie)
        }
    }

    fun setMovie(movie: Movie) {
        this.movie = movie
        binding.txtTitleMovie.text = movie.name
    }
}
