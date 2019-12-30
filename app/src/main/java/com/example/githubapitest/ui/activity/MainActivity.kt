package com.example.githubapitest.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.githubapitest.R
import com.example.githubapitest.model.Users
import com.example.githubapitest.model.ViewEvents
import com.example.githubapitest.ui.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val model by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model.getUsers()

        model.viewState.observe(this, Observer {
            when (it) {
                is ViewEvents.SucessGetUsers -> x(it.users)
            }

        })
    }


    fun x(list: List<Users>?) {
        list.toString()
    }
}
