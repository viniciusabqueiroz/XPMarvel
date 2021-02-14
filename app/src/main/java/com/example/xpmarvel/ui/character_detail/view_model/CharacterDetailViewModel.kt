package com.example.xpmarvel.ui.character_detail.view_model

import androidx.lifecycle.ViewModel
import com.example.xpmarvel.domain.models.CharacterModel
import javax.inject.Inject

class CharacterDetailViewModel @Inject constructor(
) : ViewModel() {

    private lateinit var character: CharacterModel

    fun setCharacter(character: CharacterModel) {
        this.character = character
    }

    fun getImage(): String = character.image

    fun getDesc(): String = character.description

    fun hasComics(): Boolean = character.comics.isEmpty()

    fun hasSeries(): Boolean = character.comics.isEmpty()
}