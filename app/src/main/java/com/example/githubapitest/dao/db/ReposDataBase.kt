package com.example.githubapitest.dao.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubapitest.model.Repos

@Dao
interface ReposDataBase {

    @get:Query("SELECT * FROM repos")
    val all: List<Repos>

    @Query("SELECT * FROM repos WHERE id = (:id)")
    fun loadIconById(id: Int): Repos

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(repos: List<Repos>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(repo: Repos)

    @Query("DELETE FROM repos")
    fun deleteAll()

}