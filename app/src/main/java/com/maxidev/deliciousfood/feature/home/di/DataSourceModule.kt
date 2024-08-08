package com.maxidev.deliciousfood.feature.home.di

import com.maxidev.deliciousfood.core_remote.data.remote.ApiService
import com.maxidev.deliciousfood.feature.home.data.datasource.RandomMealDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun providesRandomMealDataSource(api: ApiService): RandomMealDataSource =
        RandomMealDataSource(api = api)
}