package com.example.githubapitest.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.githubapitest.repository.usecases.GetUsers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainActivityViewModel(private val repo: GetUsers) : ViewModel() {


    fun getUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repo.execute()
                Log.d("deu bom", response.toString())
            } catch (exception: Exception) {
                Log.d("fodeu", exception.message)
            }
        }
    }
}
