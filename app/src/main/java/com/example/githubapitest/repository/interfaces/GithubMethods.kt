package com.example.githubapitest.repository.interfaces

import com.example.githubapitest.model.Users
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubMethods {

    @GET("/users")
    suspend fun getUserList(): Response<Users>
}