package com.maxidev.deliciousfood.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.maxidev.deliciousfood.data.local.dao.DetailsDao
import com.maxidev.deliciousfood.data.local.dao.FavoriteDao
import com.maxidev.deliciousfood.data.local.dao.SearchMealDao
import com.maxidev.deliciousfood.data.local.dao.remote_key_dao.SearchMealRemoteKeyDao
import com.maxidev.deliciousfood.data.local.entity.DetailsEntity
import com.maxidev.deliciousfood.data.local.entity.SearchMealEntity
import com.maxidev.deliciousfood.data.local.entity.remote_key_entity.SearchMealRemoteKeyEntity

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