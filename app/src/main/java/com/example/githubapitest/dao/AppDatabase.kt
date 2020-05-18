package com.example.githubapitest.dao


import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.githubapitest.MainApplication
import com.example.githubapitest.dao.db.ReposDataBase
import com.example.githubapitest.helper.RoomConverters

import com.example.githubapitest.model.Repos

@Database(entities = [Repos::class], version = 1, exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun reposDataBase(): ReposDataBase

    companion object {

        private lateinit var sInstance: AppDatabase

        @Synchronized
        fun getInstance(): AppDatabase {
            sInstance = Room
                .databaseBuilder(MainApplication.context, AppDatabase::class.java, "androidBD")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
            return sInstance
        }
    }
}

