package com.mirabilis.myfirstappmovie.data

import android.content.Context
import com.mirabilis.myfirstappmovie.data.keyvalue.KeyValueData
import java.lang.ref.WeakReference

class OldLocalDataSource(val context: WeakReference<Context>) {

    private val keyEmail = "email"
    private val keyToken = "token"

    private val keyValueData = KeyValueData(context, "movie-shared-preferences")

    fun saveUser(email: String, token: String): Boolean {
        keyValueData.write(keyEmail, email)
        keyValueData.write(keyToken, token)
        return true
    }

    fun getToken() = keyValueData.readString(keyToken)
    fun getEmail() = keyValueData.readString(keyEmail)

    fun deleteUser() = keyValueData.remove(keyEmail) && keyValueData.remove(keyToken)

    companion object {
        var dataSource: OldLocalDataSource? = null

        fun init(context: Context): OldLocalDataSource? {
            if (dataSource == null) {
                dataSource = OldLocalDataSource(WeakReference(context))
            }
            return dataSource
        }
    }
}
