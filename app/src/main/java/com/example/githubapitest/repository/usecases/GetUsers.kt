package com.example.githubapitest.repository.usecases

import com.example.githubapitest.model.Users
import com.example.githubapitest.repository.RetrofitBuilder
import retrofit2.Response

class GetUsers {
    suspend fun execute(): Response<Users> {
        val apiService = RetrofitBuilder().service
        return apiService.getUserList()
    }
}
