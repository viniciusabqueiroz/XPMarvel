package com.example.xpmarvel.domain.repositories

import com.example.xpmarvel.utils.Success

sealed class CharacterRepositorySuccess : Success.FeatureSuccess() {
    object PersistenceSuccess : CharacterRepositorySuccess()
}