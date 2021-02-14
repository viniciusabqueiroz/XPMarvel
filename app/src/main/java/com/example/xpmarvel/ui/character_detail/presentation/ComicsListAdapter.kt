package com.example.xpmarvel.ui.character_detail.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xpmarvel.databinding.ComicsSeriesItemBinding
import com.example.xpmarvel.domain.models.ComicModel

class ComicsListAdapter
    : ListAdapter<ComicModel, ComicsListAdapter.ViewHolder>(
    ComicDiffCallback()
) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.executePendingBindings()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ComicsSeriesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: ComicModel) {
            binding.name = character.name
            binding.srcImage = character.resourceURI
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ComicsSeriesItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class ComicDiffCallback : DiffUtil.ItemCallback<ComicModel>() {
    override fun areItemsTheSame(oldItem: ComicModel, newItem: ComicModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: ComicModel,
        newItem: ComicModel
    ): Boolean {
        return oldItem.equals(newItem)
    }
}
