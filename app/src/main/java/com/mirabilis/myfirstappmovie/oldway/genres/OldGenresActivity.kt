package com.mirabilis.myfirstappmovie.oldway.genres

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.mirabilis.myfirstappmovie.databinding.OldGenresActivityBinding
import com.mirabilis.myfirstappmovie.oldway.movies.OldMoviesActivity


/**
 * Activity da tela de generos de filme
 */
class OldGenresActivity : AppCompatActivity() {

    private lateinit var adapter: GenresAdapter

    /**
     * Binding de todos os componentes visuais (novo)
     */
    private val binding by lazy {
        OldGenresActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Binding de todos os componentes visuais (antigo)
         */
        /*
        setContentView(R.layout.old_genres_activity)

        val progress: ProgressBar = findViewById(R.id.progress)
        val btnTryAgain: Button = findViewById(R.id.btnTryAgain)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        */


        setContentView(binding.root)

        binding.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        /**
         * Criar o adapter que será responsavel pela lista na tela
         * Inicialmente criado com um array VAZIO
         */
        this.adapter = GenresAdapter(genres = arrayOf(), onClick = { showMoviesActivity(it) })


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
        val viewModel: OldGenresViewModel by viewModels()

        /**
         * Cria a ação do botão try again
         */
        binding.btnTryAgain.setOnClickListener {
            binding.btnTryAgain.visibility = View.GONE
            viewModel.loadGenres()
        }

        /**
         * A activity inicia a observação dos items observáveis do view model
         */
        viewModel.getGenres().observe(this) { genres ->
            Log.d("APP", "$genres")

            adapter.setGenres(genres)
        }

        viewModel.isLoading().observe(this) { isLoading ->
            binding.progress.visibility = if (isLoading) { View.VISIBLE } else { View.GONE }
        }

        viewModel.hasError().observe(this) {
            if (it == null) return@observe

            binding.btnTryAgain.visibility = View.VISIBLE
            val snackBar = Snackbar.make(
                findViewById(R.id.oldGenresRoot),
                R.string.genres_error,
                Snackbar.LENGTH_SHORT
            )

            snackBar.setAction(R.string.ok) { viewModel.clearError() }
            snackBar.show()
        }
    }

    /**
     * O proposito é chamar outra activity e passar como parametro o genero id
     */
    private fun showMoviesActivity(genreId: Long?) {
        val intent = Intent(baseContext, OldMoviesActivity::class.java)
        intent.putExtra("genreId", genreId)
        startActivity(intent)
    }
}

