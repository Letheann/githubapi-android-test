package com.example.githubapitest.viewmodel

import android.content.Intent
import com.example.githubapitest.model.repos

class DetailsViewModel : BaseViewModel() {
    fun getReposByIntent(intent: Intent): repos = intent.getSerializableExtra("repos") as repos
}