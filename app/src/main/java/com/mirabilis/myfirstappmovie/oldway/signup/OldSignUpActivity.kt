package com.mirabilis.myfirstappmovie.oldway.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.material.snackbar.Snackbar
import com.mirabilis.myfirstappmovie.databinding.OldSignupActivityBinding
import com.mirabilis.myfirstappmovie.extension.gone
import com.mirabilis.myfirstappmovie.extension.visible
import com.mirabilis.myfirstappmovie.oldway.terms.OldTermsActivity
import com.mirabilis.myfirstappmovie.validator.EmailValidator
import com.mirabilis.myfirstappmovie.validator.PasswordValidator

class OldSignUpActivity : AppCompatActivity() {
    private val binding by lazy {
        OldSignupActivityBinding.inflate(layoutInflater)
    }

    private var emailValid = false
    private var passwordValidAndEqual = false
    private var checkedTerms = false

    /**
     * Cria o view model
     */
    val viewModel: OldSignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { onBackPressedDispatcher.onBackPressed() }

        binding.txtEmail.addTextChangedListener { editable ->
            val email = editable.toString()
            emailValid = EmailValidator().isValid(email)
            verifyButton()
        }

        binding.txtPassword.addTextChangedListener { editable ->
            val password = editable.toString()
            passwordValidAndEqual = PasswordValidator().isValidAndEqual(
                password = password,
                confirmationPassword = binding.txtRepeatPass.text.toString()
            )
            verifyButton()
        }

        binding.txtRepeatPass.addTextChangedListener { editable ->
            val confirmationPassword = editable.toString()
            passwordValidAndEqual = PasswordValidator().isValidAndEqual(
                password = binding.txtPassword.text.toString(),
                confirmationPassword = confirmationPassword
            )
            verifyButton()
        }

        binding.checkboxTerms.setOnCheckedChangeListener { _, isChecked ->
            checkedTerms = isChecked
            verifyButton()
        }

        binding.readTerms.setOnClickListener { showTermsActivity() }

        binding.btnUserRegistration.setOnClickListener {
            viewModel.signUp(
                binding.txtEmail.text.toString(),
                binding.txtPassword.text.toString()
            )
        }

        viewModel.isLoading().observe(this) { isLoading ->
            if (isLoading) {
                binding.form.gone()
                binding.progress.visible()
            } else {
                binding.form.visible()
                binding.progress.gone()
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

    private fun showTermsActivity() {
        val intent = Intent(baseContext, OldTermsActivity::class.java)
        startActivity(intent)
    }

    private fun verifyButton() {
        binding.btnUserRegistration.isEnabled = emailValid && passwordValidAndEqual && checkedTerms
    }
}