package com.mirabilis.myfirstappmovie.oldway.terms

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mirabilis.myfirstappmovie.databinding.OldTermsActivityBinding

class OldTermsActivity : AppCompatActivity() {
    private val binding by lazy {
        OldTermsActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnClose.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }
}