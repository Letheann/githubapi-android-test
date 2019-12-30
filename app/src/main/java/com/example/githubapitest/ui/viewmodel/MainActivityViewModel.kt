package com.example.githubapitest.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubapitest.model.Users
import com.example.githubapitest.model.ViewEvents
import com.example.githubapitest.repository.usecases.GetUsers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainActivityViewModel(private val repo: GetUsers) : BaseViewModel() {

    private val state = MutableLiveData<ViewEvents>()
    val viewState: LiveData<ViewEvents> = state

    fun getUsers() {
        launch {
            withContext(IO) {
                try {
                    val response = repo.execute()
                    state.postValue(ViewEvents.SucessGetUsers(response))
                } catch (exception: Exception) {
                    Log.d("fodeu", exception.message)
                }
            }
        }
    }
}
