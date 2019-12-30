package com.example.githubapitest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapitest.R
import com.example.githubapitest.model.Users
import kotlinx.android.synthetic.main.users_layout.view.*

class UsersAdapter :
    RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private var userList: ArrayList<Users>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.users_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = userList?.get(position)

        holder.itemView.userName.text = user?.login
        holder.itemView.userState.text = user?.htmlUrl

        //Loading the image using Glide
        val context = holder.itemView.profileImage.context
        Glide.with(context)
            .load(user?.avatarUrl)
            .into(holder.itemView.profileImage)

    }

    fun setItem(list: ArrayList<Users>?) {
        userList = list ?: ArrayList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList?.size ?: 0
    }

}
