package com.example.githubapitest.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubapitest.R
import com.example.githubapitest.view.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val model by viewModel<MainActivityViewModel>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model.getUsers()
    }
}
