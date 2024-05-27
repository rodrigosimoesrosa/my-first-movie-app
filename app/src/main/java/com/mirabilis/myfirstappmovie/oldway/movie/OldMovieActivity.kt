package com.mirabilis.myfirstappmovie.oldway.movie

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.mirabilis.myfirstappmovie.R
import com.mirabilis.myfirstappmovie.databinding.OldMovieActivityBinding
import com.mirabilis.myfirstappmovie.domain.entity.Movie
import com.mirabilis.myfirstappmovie.oldway.signin.OldSignInViewModel

class OldMovieActivity : AppCompatActivity() {

    private val binding by lazy {
        OldMovieActivityBinding.inflate(layoutInflater)
    }
    /**
     * Cria o view model
     */
    val viewModel: OldMovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        val movie: Movie? = intent.extras?.getParcelable("movie")
        viewModel.check(movie?.id)

        Log.d("APP", "movieId recebido de outra activity $movie")
        binding.titleMovie.text = movie?.name
        binding.sinopseTexto.text = movie?.sinopse
        binding.imagePoster.load(movie?.getImagePath()) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_agenda)
        }

        binding.buttonFavorito.setOnClickListener {
            viewModel.executeFavorite(movie?.id)
        }

        binding.dataLancamentoTexto.text = movie?.releaseDate
        binding.popularidadeTexto.text = movie?.popularity.toString()

        viewModel.isFavoritado().observe(this) {
            if (it) binding.buttonFavorito.setColorFilter(R.color.purple_700)
            else binding.buttonFavorito.setColorFilter(android.R.color.darker_gray)
        }
    }

    override fun onRestart() {
        super.onRestart()
    }

}
