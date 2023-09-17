package com.igor.carrefourchallenge.views.main.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.igor.carrefourchallenge.databinding.ItemUserRepositoryBinding
import com.igor.carrefourchallenge.domain.models.Repository

class UserRepositoryAdapter(
    private val callback: (Repository) -> Unit
) : RecyclerView.Adapter<UserRepositoryViewHolder>() {

    var repositories: List<Repository> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRepositoryViewHolder =
        UserRepositoryViewHolder.create(parent, callback)

    override fun getItemCount(): Int = repositories.size

    override fun onBindViewHolder(holder: UserRepositoryViewHolder, position: Int) {
        holder.bind(repositories[position])
    }
}

class UserRepositoryViewHolder(
    private val binding: ItemUserRepositoryBinding,
    private val callback: (Repository) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup, callback: (Repository) -> Unit): UserRepositoryViewHolder =
            UserRepositoryViewHolder(
                binding = ItemUserRepositoryBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                callback = callback
            )
    }

    fun bind(repository: Repository) {
        binding.name.text = repository.name

        binding.layout.setOnClickListener {
            callback(repository)
        }
    }

}