package com.example.githubapitest.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.githubapitest.R
import com.example.githubapitest.helper.extensions.*
import com.example.githubapitest.model.users.FilterParameters
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_details.toolbar
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : AppCompatActivity(), View.OnClickListener {

    private var filters: FilterParameters? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        init()
        load()
    }

    private fun init() {
        setupToolbar()
        setupAllButtons()

    }

    private fun load() {
        filters = intent.extrasBundle(FILTER) ?: FilterParameters()
        populateLayoutByFilter(filters)
    }

    private fun setupAllButtons() {
        starsButton.setOnClickListener(this)
        ascButton.setOnClickListener(this)
        descButton.setOnClickListener(this)
        forksButton.setOnClickListener(this)
        updatedButton.setOnClickListener(this)
        applyFilters.setOnClickListener(this)
        favsButton.setOnClickListener(this)
    }

    private fun populateLayoutByFilter(filters: FilterParameters?) {
        if (filters?.sort != null && filters.sort != "") {
            when (filters.sort) {
                FORKS -> {
                    updateColors(forksButton, R.color.Black, R.color.White, true)
                    updateColors(updatedButton, R.color.GrayBackground, R.color.Black, false)
                    updateColors(starsButton, R.color.GrayBackground, R.color.Black, false)
                    updateColors(favsButton, R.color.GrayBackground, R.color.Black, false)
                }
                UPDATED -> {
                    updateColors(updatedButton, R.color.Black, R.color.White, true)
                    updateColors(forksButton, R.color.GrayBackground, R.color.Black, false)
                    updateColors(starsButton, R.color.GrayBackground, R.color.Black, false)
                    updateColors(favsButton, R.color.GrayBackground, R.color.Black, false)
                }

                STARS -> {
                    updateColors(starsButton, R.color.Black, R.color.White, true)
                    updateColors(forksButton, R.color.GrayBackground, R.color.Black, false)
                    updateColors(updatedButton, R.color.GrayBackground, R.color.Black, false)
                    updateColors(favsButton, R.color.GrayBackground, R.color.Black, false)
                }
            }
        } else if (filters != null && filters.favorites) {
            updateColors(favsButton, R.color.Black, R.color.White, true)
            updateColors(forksButton, R.color.GrayBackground, R.color.Black, false)
            updateColors(updatedButton, R.color.GrayBackground, R.color.Black, false)
            updateColors(starsButton, R.color.GrayBackground, R.color.Black, false)
        }

        if (filters?.order != null && filters.order != "") {
            when (filters.order) {
                ASC -> {
                    updateColors(ascButton, R.color.Black, R.color.White, true)
                    updateColors(descButton, R.color.GrayBackground, R.color.Black, false)
                }
                DESC -> {
                    updateColors(descButton, R.color.Black, R.color.White, true)
                    updateColors(ascButton, R.color.GrayBackground, R.color.Black, false)
                }
            }
        }

    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.filters)
        toolbar.setNavigationIcon(R.drawable.ic_left_arrow)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ascButton -> {
                updateColors(ascButton, R.color.Black, R.color.White, true)
                updateColors(descButton, R.color.GrayBackground, R.color.Black, false)
                filters?.order = ASC
            }
            R.id.descButton -> {
                updateColors(descButton, R.color.Black, R.color.White, true)
                updateColors(ascButton, R.color.GrayBackground, R.color.Black, false)
                filters?.order = DESC
            }
            R.id.forksButton -> {
                updateColors(forksButton, R.color.Black, R.color.White, true)
                updateColors(updatedButton, R.color.GrayBackground, R.color.Black, false)
                updateColors(starsButton, R.color.GrayBackground, R.color.Black, false)
                updateColors(favsButton, R.color.GrayBackground, R.color.Black, false)
                updateFilters(false , FORKS)
            }
            R.id.updatedButton -> {
                updateColors(updatedButton, R.color.Black, R.color.White, true)
                updateColors(forksButton, R.color.GrayBackground, R.color.Black, false)
                updateColors(starsButton, R.color.GrayBackground, R.color.Black, false)
                updateColors(favsButton, R.color.GrayBackground, R.color.Black, false)
                updateFilters(false , UPDATED)
            }
            R.id.starsButton -> {
                updateColors(starsButton, R.color.Black, R.color.White, true)
                updateColors(forksButton, R.color.GrayBackground, R.color.Black, false)
                updateColors(updatedButton, R.color.GrayBackground, R.color.Black, false)
                updateColors(favsButton, R.color.GrayBackground, R.color.Black, false)
                updateFilters(false , STARS)
            }
            R.id.favsButton -> {
                updateColors(favsButton, R.color.Black, R.color.White, true)
                updateColors(forksButton, R.color.GrayBackground, R.color.Black, false)
                updateColors(updatedButton, R.color.GrayBackground, R.color.Black, false)
                updateColors(starsButton, R.color.GrayBackground, R.color.Black, false)
                updateFilters(true , null)
            }

            R.id.applyFilters -> {
                setResult(0, Intent().putExtra(FILTERS_RESULT, filters))
                finish()
            }
        }
    }


   private fun updateFilters(fav : Boolean, sort: String?){
       filters?.sort = sort
       filters?.favorites = fav
    }

    private fun updateColors(
        view: MaterialButton,
        colorButton: Int,
        colorText: Int,
        icon: Boolean
    ) {
        view.setTextColor(
            ContextCompat.getColor(
                this,
                colorText
            )
        )
        view.loadColorBackGround(colorButton)

        if (icon) {
            view.loadColorIconTint(R.color.White)
            view.loadIcon(R.drawable.ic_confirm)
        } else {
            view.icon = null
        }

    }

    companion object {
        const val FILTER = "filter"
        const val FILTERS_RESULT = "filters_result"
        const val ASC = "asc"
        const val DESC = "desc"
        const val FORKS = "forks"
        const val UPDATED = "updated"
        const val STARS = "stars"
    }
}


