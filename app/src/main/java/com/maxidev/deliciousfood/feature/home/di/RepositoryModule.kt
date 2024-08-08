package com.maxidev.deliciousfood.feature.home.di

import com.maxidev.deliciousfood.feature.home.data.repository.FilterByRepositoryImpl
import com.maxidev.deliciousfood.feature.home.data.repository.HomeRepositoryImpl
import com.maxidev.deliciousfood.feature.home.domain.repository.FilterByRepository
import com.maxidev.deliciousfood.feature.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHomeRepository(repository: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindFilterByRepository(repository: FilterByRepositoryImpl): FilterByRepository
}