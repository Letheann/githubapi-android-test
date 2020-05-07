package com.example.githubapitest.ui.holder


import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapitest.R
import com.example.githubapitest.helper.extensions.loadFromUrl
import com.example.githubapitest.model.Repos
import com.example.githubapitest.ui.activity.DetailsActivity
import kotlinx.android.synthetic.main.users_layout.view.*
import org.joda.time.DateTime
import org.joda.time.Days
import java.util.*

class UsersHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(
        user: Repos?,
        context: Context,
        clickListener: (Repos?) -> Unit
    ) {
        itemView.userName.text = user?.name
        itemView.starValue.text = user?.stargazersCount.toString()
        itemView.followersValue.text = user?.watchersCount.toString()
        itemView.forksValue.text = user?.forksCount.toString()
        itemView.DataValue.text = context.getString(
            R.string.dias, Days.daysBetween(
                DateTime(user?.createdAt),
                DateTime(Date())
            ).days.toString()
        )
        user?.owner?.avatarUrl?.let { itemView.profileImage.loadFromUrl(it) }
        itemView.cardItem.setOnClickListener {
            clickListener.invoke(user)
        }
    }
}