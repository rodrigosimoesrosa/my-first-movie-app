package com.mirabilis.myfirstappmovie.oldway.main.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirabilis.myfirstappmovie.data.OldLocalUserDataSource
import com.mirabilis.myfirstappmovie.data.OldRemoteDataSource
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {

    private val remoteDataSource = OldRemoteDataSource()

    private val _success: MutableLiveData<Boolean> = MutableLiveData()
    fun isSuccess(): LiveData<Boolean> = _success

    private val _error: MutableLiveData<Throwable?> = MutableLiveData()
    fun hasError(): LiveData<Throwable?> = _error

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    fun isLoading(): LiveData<Boolean> = _loading

    fun isAuthorized(): Boolean {
        val email = OldLocalUserDataSource.dataSource?.getEmail()
        val token = OldLocalUserDataSource.dataSource?.getToken()
        return email !== null && token !== null
    }

    fun signOut() {
        //1 consultar o servidor para sair da sessão
        //2 Ao ser autorizado o proximo passo é remover os dados LOCAIS (sharedpreference)
        viewModelScope.launch {
            try {
                _loading.postValue(true)
                val token = OldLocalUserDataSource.dataSource?.getToken()
                val response = remoteDataSource.signOut(token)

                if (response.success) {
                    OldLocalUserDataSource.dataSource?.deleteUser()
                }

                _success.postValue(response.success)
            } catch (ex: Exception) {
                _success.postValue(false)
                _error.postValue(ex)
            } finally {
                _loading.postValue(false)
            }
        }
    }

    fun clearError() { _error.postValue(null) }
}
