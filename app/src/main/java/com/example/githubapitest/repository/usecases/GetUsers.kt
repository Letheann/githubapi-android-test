package com.example.githubapitest.repository.usecases

import com.example.githubapitest.helper.extensions.safeRequestCheckingNetwork
import com.example.githubapitest.model.Search
import com.example.githubapitest.repository.RetrofitBuilder
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

open class GetUsers {
    suspend fun execute(
        q: String,
        page: String,
        sort: String?,
        order: String?
    ): Search? = withContext(IO) {
        RetrofitBuilder().service.getReposList(q, page, sort, order).safeRequestCheckingNetwork()
    }


}