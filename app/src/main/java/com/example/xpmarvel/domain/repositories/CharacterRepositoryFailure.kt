package com.example.xpmarvel.domain.repositories

import com.example.xpmarvel.utils.Failure

sealed class CharacterRepositoryFailure : Failure.FeatureFailure() {
    object CharacterNotFound : CharacterRepositoryFailure()
    object PersistenceError : CharacterRepositoryFailure()
}