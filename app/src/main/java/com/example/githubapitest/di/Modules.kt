package com.example.githubapitest.di

import com.example.githubapitest.dao.AppDatabase
import com.example.githubapitest.repository.usecases.GetUsers
import com.example.githubapitest.viewmodel.DetailsViewModel
import com.example.githubapitest.viewmodel.MainActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { GetUsers() }
    single { AppDatabase.getInstance(androidContext()) }
    viewModel { MainActivityViewModel(get(), get()) }
    viewModel { DetailsViewModel(get()) }
}
