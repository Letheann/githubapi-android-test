package com.example.githubapitest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubapitest.model.ViewEvents
import com.example.githubapitest.repository.usecases.GetUsers
import com.example.githubapitest.ui.activity.FilterActivity
import com.example.githubapitest.ui.activity.MainActivity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(private val repo: GetUsers) : BaseViewModel() {

    private val state = MutableLiveData<ViewEvents>()
    fun viewState(): LiveData<ViewEvents> = state
    private var page = 2

    fun getRepos(
        page: String = MainActivity.DEFAULT_PAGE,
        q: String = MainActivity.ANDROID,
        sort: String? = "",
        order: String? = FilterActivity.DESC
    ) {
        launch {
            try {
                val response = repo.execute(q, page, sort, order)
                state.postValue(ViewEvents.SuccessGetUsers(response))
            } catch (exception: Exception) {
                print(exception)
            }
        }
    }


    fun incrementPage(): String {
        return "" + (page++) + ""
    }

    fun resetPage() {
        page = 2
    }

    fun returnPage(): Int = page
}
