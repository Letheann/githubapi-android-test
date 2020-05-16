package com.example.githubapitest.helper.extensions

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton


fun RecyclerView.onScrollStateChange(action: () -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            action.invoke()
        }
    })
}

fun ImageView.loadFromUrl(url: String) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

fun Activity.closeKeyboard() {
    val imm = this.getSystemService(Activity.INPUT_METHOD_SERVICE)
            as InputMethodManager
    imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
}

fun Activity.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT)
        .show()
}

fun View.loadColorBackGround(color: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.backgroundTintList = ContextCompat.getColorStateList(this.context, color)
    }
}

fun MaterialButton.loadColorIconTint(color: Int) {
    this.iconTint = ContextCompat.getColorStateList(this.context, color)
}

fun MaterialButton.loadIcon(icConfirm: Int) {
    this.icon = AppCompatResources.getDrawable(this.context, icConfirm)
}

