package com.mesutemre.unittestwork.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Singleton
    @Provides
    fun provideTarihteBugunDao(database: UnitTestWorkDatabase) = database.getTarihteBugunDao()
}