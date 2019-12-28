package com.example.githubapitest.repository.interfaces

import com.example.githubapitest.model.Users
import retrofit2.Call
import retrofit2.http.GET


interface GithubMethods {

    @GET("/users")
    fun getUserList(): Call<List<Users>>
}