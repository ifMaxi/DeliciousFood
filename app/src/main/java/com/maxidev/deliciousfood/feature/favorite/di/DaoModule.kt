package com.maxidev.deliciousfood.feature.favorite.di

import com.maxidev.deliciousfood.core_local.data.local.AppDataBase
import com.maxidev.deliciousfood.core_local.data.local.dao.FavoriteDao
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
    fun providesFavoriteDao(appDataBase: AppDataBase): FavoriteDao =
        appDataBase.favoriteDao()
}