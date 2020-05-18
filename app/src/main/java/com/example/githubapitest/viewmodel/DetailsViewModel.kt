package com.example.githubapitest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubapitest.dao.AppDatabase
import com.example.githubapitest.helper.extensions.add
import com.example.githubapitest.model.Repos
import com.example.githubapitest.model.ViewEvents
import kotlinx.coroutines.async

class DetailsViewModel(private val dao: AppDatabase) : BaseViewModel() {

    private val state = MutableLiveData<ViewEvents>()
    fun viewState(): LiveData<ViewEvents> = state

    fun saveRepoToFav(repos: Repos?) {
        jobs add async {
            repos?.let { dao.reposDataBase().insertItem(it) }
            state.postValue(ViewEvents.SavedDataRepo())
        }
    }
}

