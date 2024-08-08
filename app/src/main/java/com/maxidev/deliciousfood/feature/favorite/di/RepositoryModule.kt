package com.maxidev.deliciousfood.feature.favorite.di

import com.maxidev.deliciousfood.feature.favorite.data.repository.FavoriteRepositoryImpl
import com.maxidev.deliciousfood.feature.favorite.domain.repository.FavoriteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindFavoriteRepository(repository: FavoriteRepositoryImpl): FavoriteRepository
}