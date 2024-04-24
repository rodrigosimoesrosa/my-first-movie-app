package com.mirabilis.myfirstappmovie.oldway.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.mirabilis.myfirstappmovie.databinding.OldSignupActivityBinding
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
            //TODO faca cadastro do usuario, pegando as informacoes inseridas
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