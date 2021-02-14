package com.example.xpmarvel.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.xpmarvel.data.local.source.CharacterDao
import com.example.xpmarvel.data.local.models.CharacterEntity

@Database(
    entities = [
        CharacterEntity::class], version = 1, exportSchema = false
)
abstract class MarvelDB : RoomDatabase() {

    abstract fun allCharacterDao(): CharacterDao
}