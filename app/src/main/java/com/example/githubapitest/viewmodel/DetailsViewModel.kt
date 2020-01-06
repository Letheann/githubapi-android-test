package com.example.githubapitest.viewmodel

import android.content.Intent
import com.example.githubapitest.model.Repos

class DetailsViewModel : BaseViewModel() {
    fun getReposByIntent(intent: Intent): Repos? = intent.getSerializableExtra("repos") as? Repos
}