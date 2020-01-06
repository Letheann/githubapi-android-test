package com.example.githubapitest.viewmodel

import android.content.Intent
import com.example.githubapitest.model.FilterParameters

class FilterViewModel : BaseViewModel() {
    fun getReposByIntent(intent: Intent): FilterParameters =
        intent.getSerializableExtra("filter") as FilterParameters
}