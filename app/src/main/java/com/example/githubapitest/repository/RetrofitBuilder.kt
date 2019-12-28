package com.example.githubapitest.repository

import com.example.githubapitest.repository.interfaces.GithubMethods
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    private val retrofit: Retrofit

    val service: GithubMethods
        get() = retrofit.create(GithubMethods::class.java)

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    companion object {
        val BASE_URL = "https://api.github.com"
    }
}