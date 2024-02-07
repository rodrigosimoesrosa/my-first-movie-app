package com.mirabilis.myfirstappmovie.data.network

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetworkProvider {

    private const val AUTHORIZATION = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYmNlM2JlMDIwNWU0NDc5ZmIyNDQxNzBlZWU5NWU1NiIsInN1YiI6IjY1YTA3NDAyNDRlYTU0MDEyODJkYzFhNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.j7uhGgyMBzyafVhlIV5NGu7WQpXiQvVSNUMEWGHbU6o"
    private const val URL_BASE = "https://api.themoviedb.org/3/"

    private fun retrofit(
        authorization: String = AUTHORIZATION,
        urlBase: String = URL_BASE
    ): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addInterceptor(httpLoggingInterceptor)
        okHttpBuilder.addInterceptor { chain ->
            val request: Request = chain.request().newBuilder().addHeader(
                "Authorization",
                "Bearer $authorization"
            ).build()
            chain.proceed(request)
        }

        return Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpBuilder.build())
            .build()
    }

    fun api(): API {
        return retrofit().create(API::class.java)
    }
}
