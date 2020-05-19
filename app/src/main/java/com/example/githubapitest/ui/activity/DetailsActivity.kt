package com.example.githubapitest.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.githubapitest.R
import com.example.githubapitest.helper.extensions.*
import com.example.githubapitest.model.Repos
import com.example.githubapitest.model.ViewEvents
import com.example.githubapitest.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.users_layout.profileImage
import kotlinx.android.synthetic.main.users_layout.userName
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private var repos: Repos? = null
    private val viewmodel by viewModel<DetailsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        init()
        load()
    }


    private fun init() {
        repos = intent.extrasBundle(REPOS)
        setupToolbar()
        setupSaveRepo()
        initObservers()
    }

    private fun setupSaveRepo() {
        applyFilters.setOnClickListener {
            viewmodel.saveRepoToFav(repos)
        }.also { viewmodel.verifyDataIsAlreadySaved(repos) }
    }

    private fun initObservers() {
        viewmodel.viewState().observe(this) {
            when (it) {
                is ViewEvents.SavedDataRepo -> changeStateButton()
                is ViewEvents.CannotSaveDataRepo -> showErrorMessage()
            }
        }
    }

    private fun showErrorMessage() {
        toast(getString(R.string.cannot_save_in_favs))
    }

    private fun changeStateButton() {
        applyFilters.isEnabled = false
        applyFilters.loadColorBackGround(R.color.Lime)
        applyFilters.text = getString(R.string.save_fav_sucess)
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
