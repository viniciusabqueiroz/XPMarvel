package com.example.xpmarvel.ui.characters.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xpmarvel.ui.characters.view_model.CharactersListViewModel
import com.example.xpmarvel.domain.models.CharacterModel
import com.example.xpmarvel.databinding.CharacterItemBinding

class CharactersListAdapter(
    private val viewModel: CharactersListViewModel
) :
    ListAdapter<CharacterModel, CharactersListAdapter.ViewHolder>(AllCharacterDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: CharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: CharactersListViewModel, character: CharacterModel) {
            binding.viewModel = viewModel
            binding.character = character
            binding.name = character.name
            binding.srcImage = character.image
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CharacterItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class AllCharacterDiffCallback : DiffUtil.ItemCallback<CharacterModel>() {
    override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: CharacterModel,
        newItem: CharacterModel
    ): Boolean {
        return oldItem.equals(newItem)
    }
}
