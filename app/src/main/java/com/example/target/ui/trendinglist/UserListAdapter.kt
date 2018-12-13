package com.example.target.ui.trendinglist

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.target.R
import com.example.target.data.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserListAdapter(data: ArrayList<User>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var users: ArrayList<User> = data

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserViewHolder(inflater.inflate(R.layout.item_user, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user: User = users[position]
        val userViewHolder = holder as UserViewHolder
        userViewHolder.user.text = user.name
        userViewHolder.username.text = user.username
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val user = itemView.user as AppCompatTextView
        val username = itemView.username as AppCompatTextView
    }
}