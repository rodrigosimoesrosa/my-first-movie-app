package com.mirabilis.myfirstappmovie.oldway.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mirabilis.myfirstappmovie.databinding.OldSearchItemBinding
import com.mirabilis.myfirstappmovie.domain.entity.Movie

class SearchAdapter(
    private var movies: List<Movie>,
    private val onClick: (movie: Movie) -> Unit
): RecyclerView.Adapter<SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = OldSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  SearchViewHolder(binding, onClick)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val movie: Movie = movies[position]
        holder.setMovie(movie)
    }

    fun setMovies(values: List<Movie>) {
        movies = values
        notifyDataSetChanged()
    }
}