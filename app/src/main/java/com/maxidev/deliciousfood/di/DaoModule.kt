package com.maxidev.deliciousfood.di

import com.maxidev.deliciousfood.data.local.AppDataBase
import com.maxidev.deliciousfood.data.local.dao.CategoriesDao
import com.maxidev.deliciousfood.data.local.dao.DetailsDao
import com.maxidev.deliciousfood.data.local.dao.FavoriteDao
import com.maxidev.deliciousfood.data.local.dao.SearchMealDao
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

    @Provides
    @Singleton
    fun providesDetailDao(appDataBase: AppDataBase): DetailsDao =
        appDataBase.detailDao()

    @Provides
    @Singleton
    fun providesFavoriteDao(appDataBase: AppDataBase): FavoriteDao =
        appDataBase.favoriteDao()

    @Provides
    @Singleton
    fun providesCategoriesDao(appDataBase: AppDataBase): CategoriesDao =
        appDataBase.categoriesDao()
}