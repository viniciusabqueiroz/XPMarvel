package com.example.xpmarvel.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class CharacterEntity(
    @PrimaryKey
    var id: Int,
    val name: String,
    val image: String
)