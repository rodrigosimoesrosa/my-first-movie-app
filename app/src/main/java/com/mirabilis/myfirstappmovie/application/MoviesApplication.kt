package com.mirabilis.myfirstappmovie.application

import android.app.Application
import com.mirabilis.myfirstappmovie.data.OldLocalUserDataSource
import com.mirabilis.myfirstappmovie.data.database.Database

class MoviesApplication : Application() {

    override fun onCreate() {
        OldLocalUserDataSource.init(this)
        Database.newDatabase(this)
        super.onCreate()
    }
}