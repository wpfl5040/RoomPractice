package com.example.roompractice.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.wpfl5.roompractice.databinding.RowUserBinding
import com.wpfl5.roompractice.db.entity.User

class UserAdapter : BaseAdapter<User, RowUserBinding>(
    object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
) {
    override val layoutRes: Int = com.wpfl5.roompractice.R.layout.row_user
    override fun onBindViewHolder(binding: RowUserBinding, item: User) {
        binding.user = item
    }
}


