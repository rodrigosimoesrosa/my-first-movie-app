package com.mirabilis.myfirstappmovie.oldway.movies

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mirabilis.myfirstappmovie.R

/**
 * Activity da tela de filmes do genero Horror
 */
class OldMoviesActivity : AppCompatActivity() {

    //lateinit var adapter?
    private lateinit var adapter: MoviesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_old_movie)


        /**
         * Binding de todos os componentes visuais
         */

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val btnTryAgain: Button = findViewById(R.id.btnTryAgain)
        val progress: ProgressBar = findViewById(R.id.progress)


        /**
         * Criar o adapter que será responsavel pela lista na tela
         * Inicialmente criado com um array VAZIO
         */

        this.adapter = MoviesAdapter(arrayOf())
        recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager


        /**
         * Adiciona divisor entre cada item no recycler view (lista)
         */

        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )


        /**
         * Cria o view model
         */

        val viewModel: OldMoviesViewModel by viewModels()

        /**
         * Cria a ação do botão try again
         */
        btnTryAgain.setOnClickListener {
            btnTryAgain.visibility = View.GONE
            viewModel.loadMovies()
        }

        /**
         * A activity inicia a observacao dos items observaveis do view model
         */

        viewModel.getMovies().observe(this){ movies ->
            adapter.setMovies(movies)
        }

        viewModel.isLoading().observe(this) { isLoading ->
            progress.visibility = if (isLoading) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        viewModel.hasError().observe(this) {
            if (it == null) return@observe

            btnTryAgain.visibility = View.VISIBLE
            val snackBar = Snackbar.make(
                findViewById(R.id.oldMovieRoot),
                "Ocorreu um erro ao baixar os filmes!",
                Snackbar.LENGTH_SHORT
            )
            snackBar.setAction("Ok") {viewModel.clearError()}
            snackBar.show()
        }
    }
}