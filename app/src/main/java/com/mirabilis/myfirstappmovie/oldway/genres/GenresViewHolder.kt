package com.mirabilis.myfirstappmovie.oldway.genres

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.mirabilis.myfirstappmovie.R
import com.mirabilis.myfirstappmovie.domain.entity.Genre

/**
 * O viewHolder é a classe onde se faz o binding entre o código (Kotlin/Java) e o xml
 */
class GenresViewHolder(
    val view: View
) : RecyclerView.ViewHolder(view) {
    val txtTitle: AppCompatTextView = view.findViewById(R.id.txtTitle)
}
