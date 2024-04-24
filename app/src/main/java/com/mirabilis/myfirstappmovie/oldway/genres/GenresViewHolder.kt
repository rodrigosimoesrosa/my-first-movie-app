package com.mirabilis.myfirstappmovie.oldway.genres

import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.mirabilis.myfirstappmovie.databinding.OldGenreItemBinding
import com.mirabilis.myfirstappmovie.domain.entity.Genre

/**
 * O viewHolder é a classe onde se faz o binding entre o código (Kotlin/Java) e o xml
 */
class GenresViewHolder(
    //val view: View Antigo
    binding: OldGenreItemBinding,
    onClick: (genreId: Long?) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var genre: Genre? = null

    init { binding.root.setOnClickListener { onClick(genre?.id) } }

    fun setGenre(value: Genre) {
        this.genre = value

        txtTitle.text = value.name
    }

    val txtTitle: AppCompatTextView = binding.txtTitle //view.findViewById(R.id.txtTitle) antigo
}
