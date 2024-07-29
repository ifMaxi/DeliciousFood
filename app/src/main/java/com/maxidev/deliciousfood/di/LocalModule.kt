package com.maxidev.deliciousfood.di

import android.content.Context
import androidx.room.Room
import com.maxidev.deliciousfood.data.local.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providesDataBase(
        @ApplicationContext context: Context
    ): AppDataBase =
        Room.databaseBuilder(
            context = context,
            klass = AppDataBase::class.java,
            name = "app_data_base"
        )
            .fallbackToDestructiveMigration()
            .build()
}