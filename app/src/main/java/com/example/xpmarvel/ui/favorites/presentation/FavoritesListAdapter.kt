package com.example.xpmarvel.ui.favorites.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xpmarvel.domain.models.FavoriteCharacterModel
import com.example.xpmarvel.databinding.FavoriteItemBinding

class FavoritesListAdapter(
) :
    ListAdapter<FavoriteCharacterModel, FavoritesListAdapter.ViewHolder>(
        FavoriteCharacterDiffCallback()
    ) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: FavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(allCharacter: FavoriteCharacterModel) {
            binding.name = allCharacter.name
            binding.srcImage = allCharacter.image
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class FavoriteCharacterDiffCallback : DiffUtil.ItemCallback<FavoriteCharacterModel>() {
    override fun areItemsTheSame(
        oldItem: FavoriteCharacterModel,
        newItem: FavoriteCharacterModel
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: FavoriteCharacterModel,
        newItem: FavoriteCharacterModel
    ): Boolean {
        return oldItem.equals(newItem)
    }
}
