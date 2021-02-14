package com.example.xpmarvel.domain.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class FavoriteCharacterModel(
    val name: String,
    val image: String
) : Parcelable