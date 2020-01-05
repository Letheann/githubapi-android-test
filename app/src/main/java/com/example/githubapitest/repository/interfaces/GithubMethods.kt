package com.example.githubapitest.repository.interfaces

import com.example.githubapitest.model.Search
import com.example.githubapitest.model.repos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubMethods {
    @GET("/search/repositories")
    fun getReposList(@Query("q") q: String, @Query("page") since: String, @Query("per_page") per_page: String = "30"): Call<Search>
}