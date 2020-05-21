package com.example.githubapitest.repository

import com.example.githubapitest.BuildConfig
import com.example.githubapitest.helper.extensions.build
import com.example.githubapitest.helper.extensions.provideInterface
import com.example.githubapitest.repository.interfaces.GithubMethods
import retrofit2.Retrofit

class RetrofitBuilder {
    private val retrofit: Retrofit = Retrofit.Builder().build(BuildConfig.url)
    val service: GithubMethods
        get() = retrofit.provideInterface()
}