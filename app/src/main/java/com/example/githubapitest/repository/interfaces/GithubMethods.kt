package com.example.githubapitest.repository.interfaces

import com.example.githubapitest.model.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubMethods {
    @GET("/users")
    fun getUserList(@Query("since") since: String, @Query("per_page") per_page: String = "30"): Call<List<Users>>
}