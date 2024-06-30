package com.mesutemre.unittestwork.di

import com.mesutemre.unittestwork.data.remote.ITarihteBugunApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {

    @Singleton
    @Provides
    fun provideTarihteBugunApi(retrofit: Retrofit): ITarihteBugunApi =
        retrofit.create(ITarihteBugunApi::class.java)
}