package com.example.githubapitest.ui.activity

import android.app.Activity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapitest.R
import com.example.githubapitest.model.ViewEvents
import com.example.githubapitest.model.repos
import com.example.githubapitest.ui.adapter.UsersAdapter
import com.example.githubapitest.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val model by viewModel<MainActivityViewModel>()
    private lateinit var adater: UsersAdapter
    private lateinit var scrollListener: RecyclerView.OnScrollListener
    private val list = ArrayList<repos>()
    private val layoutManager = GridLayoutManager(this, 1)
    private val lastVisibleItemPosition: Int
        get() = layoutManager.findLastVisibleItemPosition()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        load()
    }

    private fun init() {
        setupToolbar()
        initObservers()
        setupRecyclerView()
        setRecyclerViewScrollListener()
        setRefreshLayoutListener()
        setEditTextActionListener()
    }

    private fun setEditTextActionListener() {
        searchNsme.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                if (searchNsme.text.length > 3) {
                    list.clear()
                    model.getRepos("1", searchNsme.text.toString())
                    swipeRefresh.isRefreshing = true
                    closeKeyboard()
                    return@OnKeyListener true
                } else {
                    closeKeyboard()
                    Toast.makeText(this, "Pesquise com pelo menos 3 caracteres", Toast.LENGTH_SHORT).show()
                    return@OnKeyListener false
                }
            }
            false
        })
    }

    private fun closeKeyboard(){
        val imm = this@MainActivity.getSystemService(Activity.INPUT_METHOD_SERVICE)
                as InputMethodManager
        imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Repositories"
    }

    private fun setRefreshLayoutListener() {
        swipeRefresh.setOnRefreshListener {
            list.clear()
            model.resetPage()
            searchNsme.setText("")
            model.getRepos("1")
        }
    }

    private fun setupRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        adater = UsersAdapter(this)
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
        model.getRepos()
        swipeRefresh.isRefreshing = true
    }

    private fun setRecyclerViewScrollListener() {
        scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager?.itemCount
                if (totalItemCount != null) {
                    if (totalItemCount > 15 && totalItemCount == lastVisibleItemPosition + 1) {
                        swipeRefresh.isRefreshing = true
                        if (searchNsme.text.length > 3) {
                            model.getRepos(model.incrementPage(), searchNsme.text.toString())
                        } else {
                            model.getRepos(model.incrementPage())
                        }

                    }
                }
            }
        }
        recyclerView.addOnScrollListener(scrollListener)
    }


    private fun populateData(repos: List<repos>?) {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
        repos?.let { list.addAll(it) }
        adater.setItem(list)
    }
}
