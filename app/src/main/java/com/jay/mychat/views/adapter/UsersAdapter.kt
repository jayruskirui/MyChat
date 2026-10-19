package com.jay.mychat.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jay.mychat.data.model.User
import com.jay.mychat.databinding.ItemUserBinding

class UsersAdapter(
    private var list: List<User> = emptyList(),
    private val onClick: (User) -> Unit
) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        val user = list[position]
        holder.binding.tvEmail.text = user.email
        val randChar = user.email.random()
        holder.binding.circleTv.text = randChar.toString()

        holder.itemView.setOnClickListener {
            onClick(user)
        }
    }

    override fun getItemCount(): Int = list.size

    fun updateList(newList: List<User>) {
        list = newList
        notifyDataSetChanged()
    }

    fun update(newList: List<User>) {
        updateList(newList)
    }
}
