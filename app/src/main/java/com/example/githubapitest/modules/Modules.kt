package com.example.githubapitest.modules

import com.example.githubapitest.view.viewmodel.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
val mainModule = module {
    viewModel { MainActivityViewModel() }
}
