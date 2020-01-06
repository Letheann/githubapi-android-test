package com.example.githubapitest.modules

import com.example.githubapitest.repository.usecases.GetUsers
import com.example.githubapitest.viewmodel.DetailsViewModel
import com.example.githubapitest.viewmodel.FilterViewModel
import com.example.githubapitest.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single { GetUsers() }

    viewModel { MainActivityViewModel(get()) }
    viewModel { DetailsViewModel() }
    viewModel { FilterViewModel() }

}
