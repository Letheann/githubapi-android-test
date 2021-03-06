package com.example.githubapitest

import android.app.Application
import android.content.Context
import com.example.githubapitest.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    companion object {
        const val APPLICATION = "main"
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        startKoin {
            androidContext(this@MainApplication)
            androidLogger()
            modules(mainModule)
        }
    }
}