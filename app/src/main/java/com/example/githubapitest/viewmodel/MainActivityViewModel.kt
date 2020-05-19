package com.example.githubapitest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubapitest.dao.AppDatabase
import com.example.githubapitest.helper.extensions.add
import com.example.githubapitest.model.ViewEvents
import com.example.githubapitest.repository.usecases.GetUsers
import com.example.githubapitest.ui.activity.FilterActivity
import com.example.githubapitest.ui.activity.MainActivity
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivityViewModel(private val repo: GetUsers, private val dao: AppDatabase) :
    BaseViewModel() {

    private val state = MutableLiveData<ViewEvents>()
    fun viewState(): LiveData<ViewEvents> = state
    private var page = 2

    fun getRepos(
        page: String = MainActivity.DEFAULT_PAGE,
        q: String = MainActivity.ANDROID,
        sort: String? = "",
        order: String? = FilterActivity.DESC
    ) {
        jobs add launch {
            val response = repo.execute(q, page, sort, order)
            if (response != null) {
                state.postValue(response.items?.let { ViewEvents.SuccessGetUsers(it) })
            } else {
                state.postValue(ViewEvents.ErrorRequest())
            }
        }
    }


    fun incrementPage(): String {
        return (page++).toString()
    }

    fun resetPage() {
        page = 2
    }

    fun returnPage(): Int = page


    fun loadItemsFromFav(order: String = FilterActivity.DESC) {
        jobs add async {
            state.postValue(ViewEvents.SuccessGetUsers(if (order == FilterActivity.ASC) dao.reposDataBase().all else dao.reposDataBase().all.reversed()))
        }
    }
}
