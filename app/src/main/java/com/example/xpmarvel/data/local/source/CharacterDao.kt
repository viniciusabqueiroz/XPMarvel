package com.example.xpmarvel.data.local.source

import androidx.room.*
import com.example.xpmarvel.data.local.models.CharacterEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM characterentity")
    suspend fun getFavorites(): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(vararg character: CharacterEntity)

    @Delete
    fun delete(character: CharacterEntity)
}