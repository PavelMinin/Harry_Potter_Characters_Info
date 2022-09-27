package com.example.harrypottercharactersinfo.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.size.ViewSizeResolver
import com.example.harrypottercharactersinfo.databinding.ItemCharacterBinding
import com.example.harrypottercharactersinfo.model.HPCharacter

class CharacterViewHolder (
    private val binding: ItemCharacterBinding,
    private val onCharacterClicked: (HPCharacter) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: HPCharacter) {
        with(binding) {
            image.load(character.imageUrl) {
                scale(Scale.FIT)
                size(ViewSizeResolver(root))
            }
            textName.text = character.name

            root.setOnClickListener {
                onCharacterClicked(character)
            }
        }
    }
}