package com.mirabilis.myfirstappmovie.oldway.movies

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.mirabilis.myfirstappmovie.R

/**
 * O viewHolder é a classe onde se faz o binding entre o código (Kotlin/Java) e o xml
 */
class MovieViewHolder (
    val view: View
) : RecyclerView.ViewHolder(view){
    val txtTitleMovie: AppCompatTextView = view.findViewById(R.id.txtTitleMovie)

}