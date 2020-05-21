package com.example.githubapitest.dao


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.githubapitest.MainApplication
import com.example.githubapitest.dao.db.ReposDataBase
import com.example.githubapitest.helper.utils.RoomConverters

import com.example.githubapitest.model.users.Repos

@Database(entities = [Repos::class], version = 2, exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun reposDataBase(): ReposDataBase

    companion object {

        private lateinit var sInstance: AppDatabase

        @Synchronized
        fun getInstance(androidContext: Context): AppDatabase {
            sInstance = Room
                .databaseBuilder(androidContext, AppDatabase::class.java, "androidBD")
                .fallbackToDestructiveMigration()
                .build()
            return sInstance
        }
    }
}

