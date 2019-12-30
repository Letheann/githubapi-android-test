package com.example.githubapitest.repository.usecases

import com.example.githubapitest.model.Users
import com.example.githubapitest.repository.RetrofitBuilder
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class GetUsers {
    fun execute(): List<Users>? =
        RetrofitBuilder().service.getUserList().execute().body()
}


