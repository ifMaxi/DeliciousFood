package com.maxidev.deliciousfood.feature.home.di

import com.maxidev.deliciousfood.core_local.data.local.AppDataBase
import com.maxidev.deliciousfood.core_local.data.local.dao.SearchMealDao
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
    fun providesSearchMealDao(appDataBase: AppDataBase): SearchMealDao =
        appDataBase.searchMealDao()
}