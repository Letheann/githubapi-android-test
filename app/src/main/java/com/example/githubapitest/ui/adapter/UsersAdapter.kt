package com.example.githubapitest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapitest.R
import com.example.githubapitest.model.users.Repos
import com.example.githubapitest.ui.holder.UsersHolder
import kotlin.collections.ArrayList


class UsersAdapter(
    private val context: Context,
    private val clickListener: (Repos?) -> Unit
) :
    RecyclerView.Adapter<UsersHolder>() {



    private var reposList: ArrayList<Repos>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.users_layout, parent, false)

        return UsersHolder(itemView)
    }

    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        val user = reposList?.get(position)

       holder.bind(user, context, clickListener)

    }

    fun setItem(list: ArrayList<Repos>?) {
        reposList = list ?: ArrayList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return reposList?.size ?: 0
    }

}
