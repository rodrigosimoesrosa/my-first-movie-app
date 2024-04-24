package com.mirabilis.myfirstappmovie.oldway.main.settings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mirabilis.myfirstappmovie.databinding.FragmentSettingsBinding
import com.mirabilis.myfirstappmovie.oldway.genres.OldGenresActivity
import com.mirabilis.myfirstappmovie.oldway.signin.OldSignInActivity
import com.mirabilis.myfirstappmovie.oldway.signup.OldSignUpActivity
import com.mirabilis.myfirstappmovie.oldway.terms.OldTermsActivity

class SettingsFragment : Fragment() {

    private val binding by lazy {
        FragmentSettingsBinding.inflate(layoutInflater)
    }

    /**
     * Criar activity Termos de uso  - ok
        * ScrollView com um texto - ok
     * Criar activity SignUp (Cadastro de usuário) - ok
     *  * Email (Olhar o OldSignInActivity) - ok
     *  * Senha (Olhar o OldSignInActivity) - ok
     *  * Confirmar Senha (Olhar o OldSignInActivity) - ok
     *  * CheckBox Aceita os termos - ok
     *  * Botão confirmar (Cadastrar) - ok
     */

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

        binding.btnSignOut.setOnClickListener {

        }

        binding.btnProfile.setOnClickListener {

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