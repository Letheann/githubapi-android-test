package com.example.githubapitest.ui.activity

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.example.githubapitest.R
import com.example.githubapitest.model.FilterParameters
import com.example.githubapitest.viewmodel.FilterViewModel
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_details.toolbar
import kotlinx.android.synthetic.main.activity_filter.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilterActivity : AppCompatActivity(), View.OnClickListener {

    private val model by viewModel<FilterViewModel>()
    private var filters = FilterParameters()
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
        filters = model.getReposByIntent(intent)
        populateLayoutByFilter(filters)
    }

    private fun setupAllButtons() {
        starsButton.setOnClickListener(this)
        ascButton.setOnClickListener(this)
        descButton.setOnClickListener(this)
        forksButton.setOnClickListener(this)
        updatedButton.setOnClickListener(this)
        applyFilters.setOnClickListener(this)
    }

    private fun populateLayoutByFilter(filters: FilterParameters) {
        if (filters.sort != null && filters.sort != "") {
            when (filters.sort) {
                "forks" -> {
                    updateColors(forksButton, R.color.Black, R.color.White, true)
                    updateColors(updatedButton, R.color.GrayBackground, R.color.Black, false)
                    updateColors(starsButton, R.color.GrayBackground, R.color.Black, false)
                }
                "updated" -> {
                    updateColors(updatedButton, R.color.Black, R.color.White, true)
                    updateColors(forksButton, R.color.GrayBackground, R.color.Black, false)
                    updateColors(starsButton, R.color.GrayBackground, R.color.Black, false)
                }

                "stars" -> {
                    updateColors(starsButton, R.color.Black, R.color.White, true)
                    updateColors(forksButton, R.color.GrayBackground, R.color.Black, false)
                    updateColors(updatedButton, R.color.GrayBackground, R.color.Black, false)
                }
            }
        }

        if (filters.order != null && filters.order != "") {
            when (filters.order) {
                "asc" -> {
                    updateColors(ascButton, R.color.Black, R.color.White, true)
                    updateColors(descButton, R.color.GrayBackground, R.color.Black, false)
                }
                "desc" -> {
                    updateColors(descButton, R.color.Black, R.color.White, true)
                    updateColors(ascButton, R.color.GrayBackground, R.color.Black, false)
                }
            }
        }

    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Filtros"
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
                filters.order = "asc"
            }
            R.id.descButton -> {
                updateColors(descButton, R.color.Black, R.color.White, true)
                updateColors(ascButton, R.color.GrayBackground, R.color.Black, false)
                filters.order = "desc"
            }
            R.id.forksButton -> {
                updateColors(forksButton, R.color.Black, R.color.White, true)
                updateColors(updatedButton, R.color.GrayBackground, R.color.Black, false)
                updateColors(starsButton, R.color.GrayBackground, R.color.Black, false)
                filters.sort = "forks"
            }
            R.id.updatedButton -> {
                updateColors(updatedButton, R.color.Black, R.color.White, true)
                updateColors(forksButton, R.color.GrayBackground, R.color.Black, false)
                updateColors(starsButton, R.color.GrayBackground, R.color.Black, false)
                filters.sort = "updated"
            }
            R.id.starsButton -> {
                updateColors(starsButton, R.color.Black, R.color.White, true)
                updateColors(forksButton, R.color.GrayBackground, R.color.Black, false)
                updateColors(updatedButton, R.color.GrayBackground, R.color.Black, false)
                filters.sort = "stars"
            }

            R.id.applyFilters -> {
                setResult(0, Intent().putExtra("filters", filters))
                finish()
            }
        }
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
        view.background.setColorFilter(
            ContextCompat.getColor(
                this,
                colorButton
            ), PorterDuff.Mode.SRC_ATOP
        )
        if (icon) {
            view.iconTint = ContextCompat.getColorStateList(this, R.color.White)
            view.icon = AppCompatResources.getDrawable(this, R.drawable.ic_confirm)
        } else {
            view.icon = null
        }

    }
}
