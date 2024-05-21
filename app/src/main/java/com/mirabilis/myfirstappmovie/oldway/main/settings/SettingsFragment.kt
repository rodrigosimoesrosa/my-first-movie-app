package com.mirabilis.myfirstappmovie.oldway.main.settings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.mirabilis.myfirstappmovie.databinding.FragmentSettingsBinding
import com.mirabilis.myfirstappmovie.extension.gone
import com.mirabilis.myfirstappmovie.extension.visible
import com.mirabilis.myfirstappmovie.oldway.genres.OldGenresActivity
import com.mirabilis.myfirstappmovie.oldway.signin.OldSignInActivity
import com.mirabilis.myfirstappmovie.oldway.signin.OldSignInViewModel
import com.mirabilis.myfirstappmovie.oldway.signup.OldSignUpActivity
import com.mirabilis.myfirstappmovie.oldway.terms.OldTermsActivity

class SettingsFragment : Fragment() {


    private val binding by lazy {
        FragmentSettingsBinding.inflate(layoutInflater)
    }

    /**
     * Cria o view model
     */
    val viewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("APP", "SettingsFragment onViewCreated")

        binding.btnSignIn.setOnClickListener { showSignInActivity() }

        binding.btnSignUp.setOnClickListener { showSignUpActivity() }

        binding.btnTerms.setOnClickListener { showTermsActivity() }

        binding.btnSignOut.setOnClickListener { viewModel.signOut() }

        binding.btnProfile.setOnClickListener {}

        viewModel.isSuccess().observe(viewLifecycleOwner) { success ->
            if (success == null) return@observe

            if (success) {
                binding.loggedUser.gone()
                binding.noLoggedUser.visible()
            }
        }

        viewModel.hasError().observe(viewLifecycleOwner) { error ->
            if (error == null) return@observe

            val snackBar = Snackbar.make(
                binding.root,
                error.message!!,
                Snackbar.LENGTH_SHORT
            )
            snackBar.setAction("Ok") { viewModel.clearError() }
            snackBar.show()
        }

        viewModel.isLoading().observe(viewLifecycleOwner) { isLoading ->
            if (isLoading == null) return@observe
            if (isLoading) binding.progressBar.visible() else binding.progressBar.gone()
        }
    }

    private fun showSignInActivity() {
        val intent = Intent(context, OldSignInActivity::class.java)
        startActivity(intent)
    }

    private fun showTermsActivity(){
        val intent = Intent(context, OldTermsActivity::class.java)
        startActivity(intent)
    }

    private fun showSignUpActivity(){
        val intent = Intent(context, OldSignUpActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("APP", "SettingsFragment onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("APP", "SettingsFragment onDestroy")
    }

    override fun onResume() {
        super.onResume()
        Log.d("APP", "SettingsFragment onResume")
        if (viewModel.isAuthorized()) {
            binding.loggedUser.visible()
            binding.noLoggedUser.gone()
        } else {
            binding.loggedUser.gone()
            binding.noLoggedUser.visible()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("APP", "SettingsFragment onStart")
    }

    override fun onStop() {
        Log.d("APP", "SettingsFragment onStop")
        super.onStop()
    }

    override fun onPause() {
        super.onPause()
        Log.d("APP", "SettingsFragment onPause")
    }
}