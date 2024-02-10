package com.mirabilis.myfirstappmovie.oldway.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mirabilis.myfirstappmovie.R
import com.mirabilis.myfirstappmovie.domain.entity.Movie

class MoviesAdapter(private var movies: Array<Movie>): RecyclerView.Adapter<MovieViewHolder>() {

    /**
     * onCreateViewHolder que significa carrega em memoria o xml/layout e manda para o ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.old_movie_item, parent, false)
        return MovieViewHolder(view)
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
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
       val movie: Movie = movies[position]
       holder.txtTitleMovie.text = movie.name
    }

    /**
     * Altera o array interno do adapter para um novo
     */
    fun setMovies(values: List<Movie>?){
        this.movies = values?.toTypedArray() ?: arrayOf()
        notifyDataSetChanged()
    }

}