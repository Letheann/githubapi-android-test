package com.example.githubapitest.repository

import com.example.githubapitest.helper.extensions.build
import com.example.githubapitest.helper.extensions.provideInterface
import com.example.githubapitest.repository.interfaces.GithubMethods
import retrofit2.Retrofit

class RetrofitBuilder {
    private val retrofit: Retrofit

    val service: GithubMethods
        get() = retrofit.provideInterface()

    init {
        retrofit = Retrofit.Builder().build(BASE_URL)
    }

    companion object {
        const val BASE_URL = "https://api.github.com"
    }
}