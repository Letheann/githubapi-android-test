package com.example.githubapitest.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.githubapitest.R
import com.example.githubapitest.helper.extensions.*
import com.example.githubapitest.model.FilterParameters
import com.example.githubapitest.model.Repos
import com.example.githubapitest.model.ViewEvents
import com.example.githubapitest.ui.adapter.UsersAdapter
import com.example.githubapitest.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val adater by lazy {
        UsersAdapter(this, ::actionClickListener)
    }
    private val model by viewModel<MainActivityViewModel>()
    private var filters = FilterParameters()
    private val list = ArrayList<Repos>()
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
                    model.getRepos(
                        DEFAULT_PAGE,
                        searchNsme.text.toString(),
                        filters.sort,
                        filters.order
                    )
                    swipeRefresh.isRefreshing = true
                    closeKeyboard()
                    return@OnKeyListener true
                } else {
                    closeKeyboard()
                    toast(getString(R.string.search_minimum))
                    return@OnKeyListener false
                }
            }
            false
        })
    }


    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.repositories)
    }

    private fun setRefreshLayoutListener() {
        swipeRefresh.setOnRefreshListener {
            list.clear()
            model.resetPage()
            filters = FilterParameters()
            searchNsme.setText("")
            model.getRepos()
        }
    }

    private fun setupRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adater
    }

    private fun load() {
        getUsers()
    }

    private fun initObservers() {
        model.viewState().observe(this) {
            when (it) {
                is ViewEvents.SuccessGetUsers -> populateData(it.users?.items)
            }
        }
    }

    private fun getUsers() {
        model.getRepos()
        swipeRefresh.isRefreshing = true
    }

    private fun setRecyclerViewScrollListener() {
        recyclerView.onScrollStateChange {
            val totalItemCount = recyclerView.layoutManager?.itemCount
            if (totalItemCount != null) {
                if (totalItemCount > 15 && totalItemCount == lastVisibleItemPosition + 1) {
                    swipeRefresh.isRefreshing = true
                    if (searchNsme.text.length > 3) {
                        model.getRepos(
                            model.incrementPage(),
                            searchNsme.text.toString()
                        )
                    } else {
                        model.getRepos(
                            model.incrementPage(),
                            ANDROID,
                            filters.sort,
                            filters.order
                        )
                    }

                }
            }
        }
    }


    private fun populateData(Repos: List<Repos>?) {
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
        Repos?.let { list.addAll(it) }
        adater.setItem(list)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_right, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item -> {
                toActivityForResult<FilterActivity>(requestCode = 0) {
                    putSerializable(
                        FilterActivity.FILTER,
                        filters
                    )
                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun actionClickListener(user: Repos?) {
        toActivity<DetailsActivity> { putSerializable(DetailsActivity.REPOS, user) }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == 0 && data != null) {
            filters = data.extras(FilterActivity.FILTERS_RESULT) ?: FilterParameters()
            list.clear()
            model.resetPage()
            swipeRefresh.isRefreshing = true
            if (searchNsme.text.length > 3) {
                model.getRepos(
                    DEFAULT_PAGE,
                    searchNsme.text.toString(),
                    filters.sort,
                    filters.order
                )
            } else {
                model.getRepos(
                    DEFAULT_PAGE,
                    ANDROID,
                    filters.sort,
                    filters.order
                )
            }
        }
    }

    companion object {
        const val ANDROID = "android"
        const val DEFAULT_PAGE = "1"
    }

}
