package com.example.githubapitest.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapitest.R
import com.example.githubapitest.model.Users
import com.example.githubapitest.model.ViewEvents
import com.example.githubapitest.ui.adapter.UsersAdapter
import com.example.githubapitest.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private val model by viewModel<MainActivityViewModel>()
    private lateinit var adater: UsersAdapter
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private val list = ArrayList<Users>()
    private val layoutManager = GridLayoutManager(this, 2)
    private val lastVisibleItemPosition: Int
        get() = layoutManager.findLastVisibleItemPosition()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        load()
    }

    private fun init() {
        initObservers()
        setupRecyclerView()
        setRecyclerViewScrollListener()
    }

    private fun setupRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        adater = UsersAdapter()
        recyclerView.adapter = adater
    }

    private fun load() {
        getUsers()
    }

    private fun initObservers() {
        model.viewState.observe(this, Observer {
            when (it) {
                is ViewEvents.SuccessGetUsers -> populateData(it.users)
            }
        })
    }

    private fun getUsers() {
        model.getUsers()
    }

    private fun setRecyclerViewScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                if (totalItemCount == lastVisibleItemPosition + 1) {
                    model.getUsers(model.incrementPage())
                }
            }
        }
        recyclerView.addOnScrollListener(scrollListener)
    }


    private fun populateData(users: List<Users>?) {
        users?.let { list.addAll(it) }
        adater.setItem(list)
    }
}
