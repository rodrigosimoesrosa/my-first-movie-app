package com.mirabilis.myfirstappmovie.oldway.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mirabilis.myfirstappmovie.data.OldRemoteDataSource
import com.mirabilis.myfirstappmovie.databinding.OldSearchActivityBinding
import com.mirabilis.myfirstappmovie.domain.entity.Movie
import com.mirabilis.myfirstappmovie.oldway.movie.OldMovieActivity

class OldSearchActivity : AppCompatActivity() {

    private val dataSource = OldRemoteDataSource()

    private lateinit var adapter: SearchAdapter

    private val binding by lazy {
        OldSearchActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.txtCancelar.setOnClickListener {
            binding.txtSearch.text?.clear()
        }

        adapter = SearchAdapter(movies = listOf(), onClick = ::showMovieActivity)
        binding.recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        binding.txtSearch.addTextChangedListener { editable ->
            val search = editable.toString()
            if (search.isNotEmpty()) {
                binding.txtCancelar.visibility = View.VISIBLE
                binding.btnBack.visibility = View.GONE
            } else {
                binding.txtCancelar.visibility = View.GONE
                binding.btnBack.visibility = View.VISIBLE
            }

            val movies = dataSource.searchMoviesBy(search)
            adapter.setMovies(movies)
        }
    }

    fun showMovieActivity(filmeClicadoNoViewHolder: Movie) {
        val intent = Intent(baseContext, OldMovieActivity::class.java)
        intent.putExtra("movie", filmeClicadoNoViewHolder)
        startActivity(intent)
    }

}