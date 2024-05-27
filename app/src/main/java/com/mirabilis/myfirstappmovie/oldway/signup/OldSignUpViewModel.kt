package com.mirabilis.myfirstappmovie.oldway.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirabilis.myfirstappmovie.data.OldLocalUserDataSource
import com.mirabilis.myfirstappmovie.data.OldRemoteDataSource
import kotlinx.coroutines.launch

class OldSignUpViewModel : ViewModel() {

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

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            _loading.postValue(true)
            try {
                val response = remoteDataSource.signUp(email, password)
                if (response.email.isNullOrEmpty() || response.token.isNullOrEmpty()) {
                    _success.postValue(false)
                    _error.postValue(Throwable("O sign up não deu certo!"))
                    return@launch
                }

                OldLocalUserDataSource.dataSource?.saveUser(response.email, response.token)
                _success.postValue(true)
            } catch (e: Exception) {
                _success.postValue(false)
                _error.postValue(e)
            } finally {
                _loading.postValue(false)
            }
        }
    }
}
