package com.mirabilis.myfirstappmovie.oldway.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mirabilis.myfirstappmovie.databinding.FragmentHomeBinding
import com.mirabilis.myfirstappmovie.oldway.genres.OldGenresActivity
import com.mirabilis.myfirstappmovie.oldway.movie.OldMovieActivity
import com.mirabilis.myfirstappmovie.oldway.search.OldSearchActivity

class HomeFragment : Fragment() {

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.genresCardView.setOnClickListener { showGenresActivity() }
        Log.d("APP", "HomeFragment onViewCreated")

        binding.btnSearch.setOnClickListener { showSearchActivity() }
    }

    fun showGenresActivity() {
        val intent = Intent(context, OldGenresActivity::class.java)
        startActivity(intent)
    }

    fun showSearchActivity() {
        val intent = Intent(context, OldSearchActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("APP", "HomeFragment onCreate")
    }

    override fun onStart() {
        Log.d("APP", "HomeFragment onStart")
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("APP", "HomeFragment onDestroy")
    }

    override fun onResume() {
        super.onResume()
        Log.d("APP", "HomeFragment onResume")
    }

    override fun onStop() {
        Log.d("APP", "HomeFragment onStop")
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        Log.d("APP", "HomeFragment onPause")
    }
}