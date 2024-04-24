package com.mirabilis.myfirstappmovie.oldway.search

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mirabilis.myfirstappmovie.databinding.OldSearchItemBinding
import com.mirabilis.myfirstappmovie.domain.entity.Movie

class SearchViewHolder(
    private val binding: OldSearchItemBinding,
    onClick: (movie: Movie) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var movie: Movie

    init {
        binding.root.setOnClickListener {
            Log.d("APP", "$movie")
            onClick(movie)
        }
    }


    fun setMovie(value: Movie) {
        this.movie = value

        binding.txtItemSearch.text = movie.name
        binding.imageItemSearch.load(movie.getImagePath()) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_agenda)
        }
    }
}