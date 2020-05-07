package com.example.githubapitest.helper.extensions

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.users_layout.view.*

fun RecyclerView.onScrolled(stop: (y: Int) -> Unit) {
    var dyC = 0
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            dyC += dy
            if (dyC < 0) {
                dyC = 0
            }
            stop(dyC)
        }
    })
}

fun RecyclerView.onScrollStateChange(action: () -> Unit) {
    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            action.invoke()
        }
    })
}


fun ImageView.loadFromUrl(url : String){
    Glide.with(this.context)
        .load(url)
        .into(this)
}