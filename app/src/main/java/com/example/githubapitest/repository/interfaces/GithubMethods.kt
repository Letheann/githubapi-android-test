package com.example.githubapitest.repository.interfaces

import com.example.githubapitest.model.Search
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap


interface GithubMethods {
    @GET("/search/repositories")
    fun getReposList(
        @Query("q") q: String,
        @Query("page") since: String,
        @Query("sort") sort: String?,
        @Query("order") order: String?,
        @Query("per_page") per_page: String = "30"
    ): Call<Search>
}