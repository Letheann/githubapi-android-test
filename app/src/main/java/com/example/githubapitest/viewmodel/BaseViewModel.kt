package com.example.githubapitest.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Main + job + IO
    protected val jobs = ArrayList<Job>()


    override fun onCleared() {
        super.onCleared()
        jobs.forEach {
            if (!it.isCancelled) it.cancel()
        }
    }
}