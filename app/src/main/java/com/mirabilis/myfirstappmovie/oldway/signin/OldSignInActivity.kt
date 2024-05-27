package com.mirabilis.myfirstappmovie.oldway.signin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import com.mirabilis.myfirstappmovie.databinding.OldSigninActivityBinding
import com.mirabilis.myfirstappmovie.oldway.signup.OldSignUpViewModel
import com.mirabilis.myfirstappmovie.validator.EmailValidator
import com.mirabilis.myfirstappmovie.validator.PasswordValidator


class OldSignInActivity : AppCompatActivity() {

    private val binding by lazy {
        OldSigninActivityBinding.inflate(layoutInflater)
    }

    private var emailValid = false
    private var passwordValid = false

    /**
     * Cria o view model
     */
    val viewModel: OldSignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.txtEmail.addTextChangedListener { editable ->
            Log.d("APP", "email: ${editable.toString()}")
            val email = editable.toString()
            emailValid = EmailValidator().isValid(email)
            Log.d("APP", "email valid? $emailValid")
            verifyButton()
        }

        binding.txtPassword.addTextChangedListener { editable ->
            Log.d("APP", "password: ${editable.toString()}")
            val password = editable.toString()
            passwordValid = PasswordValidator().isValid(password)
            Log.d("APP", "password valid? $passwordValid")
            verifyButton()
        }

        binding.btnLogin.setOnClickListener {

            binding.txtEmail.onEditorAction(EditorInfo.IME_ACTION_DONE)
            binding.txtPassword.onEditorAction(EditorInfo.IME_ACTION_DONE)

            viewModel.signIn(
                binding.txtEmail.text.toString(),
                binding.txtPassword.text.toString()
            )
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

            val snackBar = Snackbar.make(
                binding.root,
                it.message!!,
                Snackbar.LENGTH_SHORT
            )
            snackBar.setAction("Ok") { viewModel.clearError() }
            snackBar.show()
        }

        viewModel.isSuccess().observe(this) { result ->
            if (result == null) return@observe

            if(result) { finish() }
        }
    }

    private fun verifyButton() {
        binding.btnLogin.isEnabled = emailValid && passwordValid
    }
}