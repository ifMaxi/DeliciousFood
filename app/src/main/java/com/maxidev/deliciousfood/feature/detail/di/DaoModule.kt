package com.maxidev.deliciousfood.feature.detail.di

import com.maxidev.deliciousfood.core_local.data.local.AppDataBase
import com.maxidev.deliciousfood.core_local.data.local.dao.DetailsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun providesDetailDao(appDataBase: AppDataBase): DetailsDao =
        appDataBase.detailDao()
}