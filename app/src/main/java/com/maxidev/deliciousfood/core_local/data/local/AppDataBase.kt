package com.maxidev.deliciousfood.core_local.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.maxidev.deliciousfood.core_local.data.local.dao.DetailsDao
import com.maxidev.deliciousfood.core_local.data.local.dao.FavoriteDao
import com.maxidev.deliciousfood.core_local.data.local.dao.SearchMealDao
import com.maxidev.deliciousfood.core_local.data.local.dao.remote_key_dao.SearchMealRemoteKeyDao
import com.maxidev.deliciousfood.core_local.data.local.entity.DetailsEntity
import com.maxidev.deliciousfood.core_local.data.local.entity.SearchMealEntity
import com.maxidev.deliciousfood.core_local.data.local.entity.remote_key_entity.SearchMealRemoteKeyEntity

@Database(
    entities = [
        SearchMealEntity::class,
        SearchMealRemoteKeyEntity::class,
        DetailsEntity::class
               ],
    version = 7,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class AppDataBase: RoomDatabase() {

    abstract fun searchMealDao(): SearchMealDao
    abstract fun searchMealRemoteKeyDao(): SearchMealRemoteKeyDao

    abstract fun detailDao(): DetailsDao

    abstract fun favoriteDao(): FavoriteDao
}