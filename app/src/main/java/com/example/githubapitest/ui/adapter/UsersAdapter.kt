package com.example.githubapitest.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapitest.R
import com.example.githubapitest.model.repos
import com.example.githubapitest.ui.activity.DetailsActivity
import com.example.githubapitest.ui.activity.MainActivity
import kotlinx.android.synthetic.main.users_layout.view.*

class UsersAdapter(private val mainActivity: MainActivity) :
    RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private var reposList: ArrayList<repos>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.users_layout, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = reposList?.get(position)

        holder.itemView.userName.text = user?.name
        holder.itemView.starValue.text = user?.stargazersCount.toString()
        holder.itemView.followersValue.text = user?.watchersCount.toString()
        holder.itemView.forksValue.text = user?.forksCount.toString()
        holder.itemView.DataValue.text = user?.createdAt

        //Loading the image using Glide
        val context = holder.itemView.profileImage.context
        Glide.with(context)
            .load(user?.owner?.avatarUrl)
            .into(holder.itemView.profileImage)

        holder.itemView.cardItem.setOnClickListener {
            val intent = Intent(mainActivity, DetailsActivity::class.java)
            intent.putExtra("repos", user)
            mainActivity.startActivity(intent)
        }

    }

    fun setItem(list: ArrayList<repos>?) {
        reposList = list ?: ArrayList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return reposList?.size ?: 0
    }

}
