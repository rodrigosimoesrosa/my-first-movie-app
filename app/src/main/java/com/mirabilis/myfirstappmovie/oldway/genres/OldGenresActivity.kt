package com.mirabilis.myfirstappmovie.oldway.genres

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
 * Activity da tela de generos de filme
 */
class OldGenresActivity : AppCompatActivity() {

    private lateinit var adapter: GenresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.old_genres_activity)

        /**
         * Binding de todos os componentes visuais
         */
        val progress: ProgressBar = findViewById(R.id.progress)
        val btnTryAgain: Button = findViewById(R.id.btnTryAgain)
        val recyclerView: RecyclerView = findViewById(R.id.genresRecyclerView)


        /**
         * Criar o adapter que será responsavel pela lista na tela
         * Inicialmente criado com um array VAZIO
         */
        this.adapter = GenresAdapter(arrayOf())
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
        val viewModel: OldGenresViewModel by viewModels()

        /**
         * Cria a ação do botão try again
         */
        btnTryAgain.setOnClickListener {
            btnTryAgain.visibility = View.GONE
            viewModel.loadGenres()
        }

        /**
         * A activity inicia a observação dos items observáveis do view model
         */
        viewModel.getGenres().observe(this) { genres ->
            adapter.setGenres(genres)
        }

        viewModel.isLoading().observe(this) { isLoading ->
            progress.visibility = if (isLoading) { View.VISIBLE } else { View.GONE }
        }

        viewModel.hasError().observe(this) {
            if (it == null) return@observe

            btnTryAgain.visibility = View.VISIBLE
            val snackBar = Snackbar.make(
                findViewById(R.id.oldGenresRoot),
                R.string.genres_error,
                Snackbar.LENGTH_SHORT
            )

            snackBar.setAction(R.string.ok) { viewModel.clearError() }
            snackBar.show()
        }
    }
}

