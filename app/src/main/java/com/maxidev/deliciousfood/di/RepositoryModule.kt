package com.maxidev.deliciousfood.di

import com.maxidev.deliciousfood.data.repository.DetailMealRepositoryImpl
import com.maxidev.deliciousfood.data.repository.FavoriteRepositoryImpl
import com.maxidev.deliciousfood.data.repository.HomeRepositoryImpl
import com.maxidev.deliciousfood.data.repository.MealRepositoryImpl
import com.maxidev.deliciousfood.domain.repository.DetailMealRepository
import com.maxidev.deliciousfood.domain.repository.FavoriteRepository
import com.maxidev.deliciousfood.domain.repository.HomeRepository
import com.maxidev.deliciousfood.domain.repository.MealRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMealRepository(repository: MealRepositoryImpl): MealRepository

    @Binds
    abstract fun bindDetailRepository(repository: DetailMealRepositoryImpl): DetailMealRepository

    @Binds
    abstract fun bindFavoriteRepository(repository: FavoriteRepositoryImpl): FavoriteRepository

    @Binds
    abstract fun bindHomeRepository(repository: HomeRepositoryImpl): HomeRepository
}