package com.example.githubapitest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubapitest.model.ViewEvents
import com.example.githubapitest.repository.usecases.GetUsers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(private val repo: GetUsers) : BaseViewModel() {

    private val state = MutableLiveData<ViewEvents>()
    val viewState: LiveData<ViewEvents> = state
    private var page = 2

    fun getUsers(since: String = "1") {
        launch {
            withContext(IO) {
                try {
                    val response = repo.execute(since)
                    state.postValue(ViewEvents.SuccessGetUsers(response))
                } catch (exception: Exception) {

                }
            }
        }
    }

    fun incrementPage(): String {
        return "" + (page ++) + ""
    }
}
