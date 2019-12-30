package com.example.githubapitest.repository.usecases

import com.example.githubapitest.model.Users
import com.example.githubapitest.repository.RetrofitBuilder

class GetUsers {
    fun execute(since: String): List<Users>? =
        RetrofitBuilder().service.getUserList(since).execute().body()
}


