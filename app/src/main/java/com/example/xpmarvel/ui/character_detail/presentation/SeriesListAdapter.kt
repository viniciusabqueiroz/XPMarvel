package com.example.xpmarvel.ui.character_detail.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.xpmarvel.databinding.ComicsSeriesItemBinding
import com.example.xpmarvel.domain.models.SeriesModel

class SeriesListAdapter
    : ListAdapter<SeriesModel, SeriesListAdapter.ViewHolder>(
    CharacterDiffCallback()
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

        fun bind(character: SeriesModel) {
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

class CharacterDiffCallback : DiffUtil.ItemCallback<SeriesModel>() {
    override fun areItemsTheSame(oldItem: SeriesModel, newItem: SeriesModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: SeriesModel,
        newItem: SeriesModel
    ): Boolean {
        return oldItem.equals(newItem)
    }
}
