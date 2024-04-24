package com.mirabilis.myfirstappmovie.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

/**
 * Armazena em memoria as informações dos filmes
 */
@Parcelize
data class Movie(val id: Int, val name: String, val diretor: String, val sinopse: String, val url: String? = null, val popularity: Double? = null, val releaseDate: String? = null) : Parcelable {
    fun getImagePath() = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/$url"
}


/**
 * IMAGEM
 */
//"https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nCbkOyOMTEwlEV0LtCOvCnwEONA.jpg"
