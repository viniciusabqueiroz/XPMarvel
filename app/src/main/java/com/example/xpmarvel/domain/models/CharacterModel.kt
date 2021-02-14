package com.example.xpmarvel.domain.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class CharacterModel(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val comics: List<ComicModel>,
    val series: List<SeriesModel>
) : Parcelable
