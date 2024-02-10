package com.mirabilis.myfirstappmovie.oldway.genres

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mirabilis.myfirstappmovie.R
import com.mirabilis.myfirstappmovie.domain.entity.Genre

/**
 * O propósito do adapter é construir cada ITEM de uma lista
 * O adapter precisa de uma fonte de DADOS
 * O viewHolder é a classe onde se faz o binding entre o código (Kotlin/Java) e o xml
 *
 * O adapter obriga você a IMPLEMENTAR as funções:
 * onCreateViewHolder que significa carrega em memoria o xml/layout e manda para o ViewHolder
 * getItemCount que é a quantidade dos items que serão criados na LISTA VISUAL
 * onBindViewHolder é o local onde conectamos os dados e o componente visual a ser criado
 */
class GenresAdapter(
    private var genres: Array<Genre>
) : RecyclerView.Adapter<GenresViewHolder>() {

    /**
     * onCreateViewHolder que significa carrega em memoria o xml/layout e manda para o ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.old_genre_item, parent, false)

        return GenresViewHolder(view)
    }

    /**
     * getItemCount que é a quantidade dos items que serão criados na LISTA VISUAL
     */
    override fun getItemCount(): Int {
        return genres.size
    }

    /**
     * onBindViewHolder é o local onde conectamos os dados e o componente visual a ser criado
     */
    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        val genre: Genre = genres[position]
        holder.txtTitle.text = genre.name
    }

    /**
     * Altera o array interno do adapter para um novo
     */
    fun setGenres(values: List<Genre>?) {
        this.genres = values?.toTypedArray() ?: arrayOf()
        notifyDataSetChanged()
    }
}
