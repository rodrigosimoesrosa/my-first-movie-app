package com.mirabilis.myfirstappmovie.oldway.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mirabilis.myfirstappmovie.databinding.OldMovieItemBinding
import com.mirabilis.myfirstappmovie.domain.entity.Movie

class MoviesAdapter(
    private var movies: Array<Movie>,
    private val onClick: (movie: Movie) -> Unit
): RecyclerView.Adapter<MoviesViewHolder>() {

    /**
     * onCreateViewHolder que significa carrega em memoria o xml/layout e manda para o ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = OldMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  MoviesViewHolder(binding, onClick)
    }

    /**
     * getItemCount que é a quantidade dos items que serão criados na LISTA VISUAL
     */
    override fun getItemCount(): Int {
        return movies.size
    }

    /**
     * onBindViewHolder é o local onde conectamos os dados e o componente visual a ser criado
     */
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
       val movie: Movie = movies[position]
        holder.setMovie(movie)
    }

    /**
     * Altera o array interno do adapter para um novo
     */
    fun setMovies(values: List<Movie>?){
        this.movies = values?.toTypedArray() ?: arrayOf()
        notifyDataSetChanged()
    }

}