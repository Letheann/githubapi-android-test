package com.example.githubapitest.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubapitest.R
import com.example.githubapitest.helper.extensions.extrasBundle
import com.example.githubapitest.helper.extensions.loadFromUrl
import com.example.githubapitest.model.Repos
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.users_layout.profileImage
import kotlinx.android.synthetic.main.users_layout.userName

class DetailsActivity : AppCompatActivity() {

    private var repos: Repos? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        init()
        load()
    }


    private fun init() {
        repos = intent.extrasBundle(REPOS)
        setupToolbar()
    }

    private fun load() {
        populateLayout()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.details)
        toolbar.setNavigationIcon(R.drawable.ic_left_arrow)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun populateLayout() {
        repos?.owner?.avatarUrl?.let { profileImage.loadFromUrl(it) }
        userName.text = repos?.name
        startValue.text = repos?.stargazersCount.toString()
        forksValueCount.text = repos?.forksCount.toString()
        watchersValue.text = repos?.watchersCount.toString()
        openIssuesValue.text = repos?.openIssuesCount.toString()
        scoreValue.text = repos?.score.toString()
    }


    companion object {
        const val REPOS = "repos"
    }
}
