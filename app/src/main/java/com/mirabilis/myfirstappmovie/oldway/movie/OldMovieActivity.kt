package com.mirabilis.myfirstappmovie.oldway.movie

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.mirabilis.myfirstappmovie.databinding.OldMovieActivityBinding
import com.mirabilis.myfirstappmovie.domain.entity.Movie

class OldMovieActivity : AppCompatActivity() {

    private val binding by lazy {
        OldMovieActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val movie: Movie? = intent.extras?.getParcelable("movie")

        Log.d("APP", "movieId recebido de outra activity $movie")
        binding.titleMovie.text = movie?.name
        binding.sinopseTexto.text = movie?.sinopse
        binding.imagePoster.load(movie?.getImagePath()) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_agenda)
        }

        binding.dataLancamentoTexto.text = movie?.releaseDate
        binding.popularidadeTexto.text = movie?.popularity.toString()
    }

    override fun onRestart() {
        super.onRestart()
    }

}
