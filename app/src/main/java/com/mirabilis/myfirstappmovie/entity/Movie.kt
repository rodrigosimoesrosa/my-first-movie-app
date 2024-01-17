package com.mirabilis.myfirstappmovie.entity

/**
 * Armazena em memoria as informações dos filmes
 */
data class Movie(val id: Int, val name: String, val url: String? = null)
