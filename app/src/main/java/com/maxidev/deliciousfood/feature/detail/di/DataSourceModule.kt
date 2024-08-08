package com.maxidev.deliciousfood.feature.detail.di

import com.maxidev.deliciousfood.core_remote.data.remote.ApiService
import com.maxidev.deliciousfood.feature.detail.data.datasource.DetailsMealDataSource
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
}