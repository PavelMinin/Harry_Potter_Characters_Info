package com.example.harrypottercharactersinfo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.harrypottercharactersinfo.databinding.ItemCharacterBinding
import com.example.harrypottercharactersinfo.databinding.ItemLoadingBinding
import com.example.harrypottercharactersinfo.model.HPCharacter
import com.example.harrypottercharactersinfo.model.PagingData

class CharacterAdapter(
    private val onCharacterClicked: (HPCharacter) -> Unit
) : ListAdapter<PagingData<HPCharacter>, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PagingData.Content -> TYPE_USER
            PagingData.Loading -> TYPE_LOADING
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_USER -> {
                CharacterViewHolder(
                    binding = ItemCharacterBinding.inflate(layoutInflater, parent, false),
                    onCharacterClicked = onCharacterClicked
                )
            }
            TYPE_LOADING -> {
                LoadingViewHolder(
                    binding = ItemLoadingBinding.inflate(layoutInflater, parent, false)
                )
            }
            else -> error("Unsupported viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val character = (getItem(position) as? PagingData.Content)?.data ?: return
        (holder as? CharacterViewHolder)?.bind(character)
    }

    companion object {

        private const val TYPE_USER = 1
        private const val TYPE_LOADING = 2

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PagingData<HPCharacter>>() {
            override fun areItemsTheSame(
                oldItem: PagingData<HPCharacter>,
                newItem: PagingData<HPCharacter>
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: PagingData<HPCharacter>,
                newItem: PagingData<HPCharacter>
            ): Boolean {
                val oldCharacter = oldItem as? PagingData.Content
                val newCharacter = newItem as? PagingData.Content
                return oldCharacter?.data == newCharacter?.data
            }
        }
    }
}