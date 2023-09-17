package com.igor.carrefourchallenge.views.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.igor.carrefourchallenge.databinding.ItemUsersBinding
import com.igor.carrefourchallenge.domain.models.User
import com.igor.carrefourchallenge.views.extensions.fetchImage

class UsersAdapter(
    private val callback: (User) -> Unit
) : RecyclerView.Adapter<UsersViewHolder>() {

    var users: List<User> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder =
        UsersViewHolder.create(parent, callback)

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(users[position])
    }

}

class UsersViewHolder(
    private val binding: ItemUsersBinding,
    private val callback: (User) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User) {
        binding.image.fetchImage(user.avatarUrl)
        binding.login.text = user.login
        binding.page.text = user.url

        binding.root.setOnClickListener {
            callback(user)
        }
    }

    companion object {
        fun create(parent: ViewGroup, callback: (User) -> Unit): UsersViewHolder = UsersViewHolder(
            binding = ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            callback = callback
        )
    }
}