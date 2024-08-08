package com.maxidev.deliciousfood.feature.detail.di

import com.maxidev.deliciousfood.feature.detail.data.repository.DetailMealRepositoryImpl
import com.maxidev.deliciousfood.feature.detail.domain.repository.DetailMealRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDetailRepository(repository: DetailMealRepositoryImpl): DetailMealRepository
}