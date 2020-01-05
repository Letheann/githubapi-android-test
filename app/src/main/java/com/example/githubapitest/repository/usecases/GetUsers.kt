package com.example.githubapitest.repository.usecases

import com.example.githubapitest.model.Search
import com.example.githubapitest.model.repos
import com.example.githubapitest.repository.RetrofitBuilder

open class GetUsers {
    fun execute(q: String, since: String):Search? =
        RetrofitBuilder().service.getReposList(q, since).execute().body()
}


