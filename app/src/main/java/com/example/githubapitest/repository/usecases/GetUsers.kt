package com.example.githubapitest.repository.usecases

import com.example.githubapitest.model.Users
import com.example.githubapitest.repository.RetrofitBuilder

class GetUsers {
     fun execute(): List<Users>? {
        return RetrofitBuilder().service.getUserList().execute().body()
    }
}
