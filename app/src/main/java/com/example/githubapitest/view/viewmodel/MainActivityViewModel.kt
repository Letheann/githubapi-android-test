package com.example.githubapitest.view.viewmodel

import androidx.lifecycle.ViewModel
import com.example.githubapitest.repository.usecases.GetUsers

class MainActivityViewModel(val repo: GetUsers) : ViewModel() {


    fun getUsers() {
        val response = repo.execute()
    }

}