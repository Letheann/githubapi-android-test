package com.example.githubapitest.helper.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, onData: (T) -> Unit) {
    return this.observe(owner, Observer { data -> data?.let { onData(data) } })
}