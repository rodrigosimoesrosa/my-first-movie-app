package com.mirabilis.myfirstappmovie.oldway.movies

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mirabilis.myfirstappmovie.R
import com.mirabilis.myfirstappmovie.databinding.ActivityOldMovieBinding
import com.mirabilis.myfirstappmovie.domain.entity.Movie
import com.mirabilis.myfirstappmovie.domain.entity.movies.genre.MovieByGenre
import com.mirabilis.myfirstappmovie.oldway.movie.OldMovieActivity

/**
 * Activity da tela de filmes
 */
class OldMoviesActivity : AppCompatActivity() {

    //lateinit var adapter?
    private lateinit var adapter: MoviesAdapter

    private val binding by lazy {
        ActivityOldMovieBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val genreId: Long = intent.extras?.getLong("genreId") ?: 27

        /**
         * Binding de todos os componentes visuais
         */
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        /**
         * Criar o adapter que será responsavel pela lista na tela
         * Inicialmente criado com um array VAZIO
         */
        this.adapter = MoviesAdapter(movies = arrayOf(), onClick = ::showMovieActivity)
        binding.recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager


        /**
         * Adiciona divisor entre cada item no recycler view (lista)
         */
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )


        /**
         * Cria o view model
         */

        val viewModel: OldMoviesViewModel by viewModels()

        /**
         * Cria a ação do botão try again
         */
        binding.btnTryAgain.setOnClickListener {
            binding.btnTryAgain.visibility = View.GONE
            viewModel.loadMovies(genreId)
        }

        /**
         * A activity inicia a observacao dos items observaveis do view model
         */

        viewModel.getMovies(genreId).observe(this) { movies ->
            adapter.setMovies(movies)
        }

        viewModel.isLoading().observe(this) { isLoading ->
            binding.progress.visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        viewModel.hasError().observe(this) {
            if (it == null) return@observe

            binding.btnTryAgain.visibility = View.VISIBLE
            val snackBar = Snackbar.make(
                findViewById(R.id.oldMovieRoot),
                "Ocorreu um erro ao baixar os filmes!",
                Snackbar.LENGTH_SHORT
            )
            snackBar.setAction("Ok") {viewModel.clearError()}
            snackBar.show()
        }
    }

    fun showMovieActivity(movie: Movie) {
        val intent = Intent(baseContext, OldMovieActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }
}