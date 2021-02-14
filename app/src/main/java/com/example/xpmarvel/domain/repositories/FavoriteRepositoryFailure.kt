package com.example.xpmarvel.domain.repositories

import com.example.xpmarvel.utils.Failure

sealed class FavoriteRepositoryFailure : Failure.FeatureFailure() {
    object PersistenceError : FavoriteRepositoryFailure()
}