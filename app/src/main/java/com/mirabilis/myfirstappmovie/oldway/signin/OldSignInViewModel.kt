package com.mirabilis.myfirstappmovie.oldway.signin

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirabilis.myfirstappmovie.data.OldLocalDataSource
import com.mirabilis.myfirstappmovie.data.OldRemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

/**
 * O view model é responsavel por gerenciar tarefas que não são de responsabilidade da TELA (ACTIVITY/FRAGMENT)
 */
class OldSignInViewModel: ViewModel() {

    /**
     * Fonte de dados Remota
     */
    private val remoteDataSource = OldRemoteDataSource()

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    fun isLoading(): LiveData<Boolean> = _loading

    private val _success: MutableLiveData<Boolean> = MutableLiveData()
    fun isSuccess(): LiveData<Boolean> = _success

    private val _error: MutableLiveData<Throwable?> = MutableLiveData()
    fun hasError(): LiveData<Throwable?> = _error

    fun clearError() { _error.postValue(null) }

    /**
     * Funcao que faz o login na fonte de dados
     */
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _loading.postValue(true)
            val response = remoteDataSource.signIn(email, password)
            Log.d("APP", "Sign In ViewModel " + response.token)
            if (response.email.isNullOrEmpty() || response.token.isNullOrEmpty()) {
                _success.postValue(false)
                _error.postValue(Throwable("O login não deu certo!"))
                return@launch
            }

            OldLocalDataSource.dataSource?.saveUser(response.email, response.token)
            _success.postValue(true)
            _loading.postValue(false)
        }
    }
}