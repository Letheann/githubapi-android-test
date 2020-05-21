package com.example.githubapitest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubapitest.dao.AppDatabase
import com.example.githubapitest.helper.extensions.add
import com.example.githubapitest.model.users.Repos
import com.example.githubapitest.model.events.ViewEvents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DetailsViewModel(private val dao: AppDatabase) : BaseViewModel() {

    private val state = MutableLiveData<ViewEvents>()
    fun viewState(): LiveData<ViewEvents> = state

    fun saveRepoToFav(repos: Repos?) {
        try {
            jobs add launch {
                repos?.let { dao.reposDataBase().insertItem(it) }
                state.postValue(ViewEvents.SavedDataRepo())
            }
        } catch (e: Exception) {
            e.toString()
        }
    }

    fun verifyDataIsAlreadySaved(repos: Repos?) {
        jobs add async(Dispatchers.IO) {
            if (repos?.id?.let { dao.reposDataBase().loadRepoById(it) } != null) {
                state.postValue(ViewEvents.SavedDataRepo())
            }
        }
    }
}



