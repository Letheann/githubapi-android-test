package com.example.githubapitest.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.githubapitest.R
import com.example.githubapitest.model.Repos
import com.example.githubapitest.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.users_layout.profileImage
import kotlinx.android.synthetic.main.users_layout.userName
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private val model by viewModel<DetailsViewModel>()
    private lateinit var repos: Repos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        init()
        load()
    }


    private fun init() {
        repos = model.getReposByIntent(intent)
        setupToolbar()
    }

    private fun load() {
        populateLayout()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Detalhes"
        toolbar.setNavigationIcon(R.drawable.ic_left_arrow)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun populateLayout() {
        Glide.with(this)
            .load(repos.owner?.avatarUrl)
            .into(profileImage)

        userName.text = repos.name
        startValue.text = repos.stargazersCount.toString()
        forksValueCount.text = repos.forksCount.toString()
        watchersValue.text = repos.watchersCount.toString()
        openIssuesValue.text = repos.openIssuesCount.toString()
        scoreValue.text = repos.score.toString()
    }
}
