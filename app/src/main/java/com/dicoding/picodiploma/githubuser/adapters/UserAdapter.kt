package com.dicoding.picodiploma.githubuser.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.githubuser.R
import com.dicoding.picodiploma.githubuser.db.User
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(private var listUsers: ArrayList<User>): RecyclerView.Adapter<UserAdapter.UserHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun setItemClicked(user: User);
    }

    inner class UserHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUsername: TextView = itemView.findViewById(R.id.tv_item_username)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvCompany: TextView = itemView.findViewById(R.id.tv_item_company)
        val tvLocation: TextView = itemView.findViewById(R.id.tv_item_location)
        val imgUser: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_users, parent, false)
        return UserHolder(view)
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user: User = listUsers[position]

        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .apply{ RequestOptions().override(100, 100) }
            .into(holder.imgUser)

        holder.tvUsername.text = user.name
        holder.tvName.text = user.name
        holder.tvCompany.text = user.company
        holder.tvLocation.text = user.location

        holder.itemView.setOnClickListener { onItemClickCallback.setItemClicked(listUsers[position]) }
    }

    fun filterList(filteredList: ArrayList<User>) {
        listUsers = filteredList
        notifyDataSetChanged()
    }
}