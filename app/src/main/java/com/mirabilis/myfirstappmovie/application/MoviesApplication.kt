package com.mirabilis.myfirstappmovie.application

import android.app.Application
import com.mirabilis.myfirstappmovie.data.OldLocalDataSource

class MoviesApplication : Application() {

    override fun onCreate() {
        OldLocalDataSource.init(this)
        super.onCreate()
    }
}