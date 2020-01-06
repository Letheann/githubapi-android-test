package com.example.githubapitest.repository.usecases

import com.example.githubapitest.model.Search
import com.example.githubapitest.repository.RetrofitBuilder

open class GetUsers {
    fun execute(
        q: String,
        page: String,
        sort: String?,
        order: String?
    ): Search? =
        RetrofitBuilder().service.getReposList(q, page, sort, order).execute().body()
}


