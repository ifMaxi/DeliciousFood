package com.maxidev.deliciousfood.di

import com.maxidev.deliciousfood.data.remote.ApiService
import com.maxidev.deliciousfood.data.repository.datasource.DetailsMealDataSource
import com.maxidev.deliciousfood.data.repository.datasource.RandomMealDataSource
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
    fun providesDetailDataSource(api: ApiService): DetailsMealDataSource =
        DetailsMealDataSource(api = api)

    @Provides
    @Singleton
    fun providesRandomMealDataSource(api: ApiService): RandomMealDataSource =
        RandomMealDataSource(api = api)
}