package com.mirabilis.myfirstappmovie.oldway.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mirabilis.myfirstappmovie.R
import com.mirabilis.myfirstappmovie.databinding.OldMainActivityBinding

class OldMainActivity : AppCompatActivity() {

    private val binding by lazy {
        OldMainActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("APP", "OldMainActivity onCreate")
        setContentView(binding.root)
        buildBottomNavigation()
    }

    private fun buildBottomNavigation() {
        val navController = findNavController(R.id.mainNavFragment)

        binding.bottomNavigation.setupWithNavController(navController)
    }

    override fun onStart() {
        Log.d("APP", "OldMainActivity onStart")
        super.onStart()
    }

    override fun onResume() {
        Log.d("APP", "OldMainActivity onResume")
        super.onResume()
    }

    override fun onRestart() {
        Log.d("APP", "OldMainActivity onRestart")
        super.onRestart()
    }

    override fun onPause() {
        Log.d("APP", "OldMainActivity onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d("APP", "OldMainActivity onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("APP", "OldMainActivity onDestroy")
        super.onDestroy()
    }


}