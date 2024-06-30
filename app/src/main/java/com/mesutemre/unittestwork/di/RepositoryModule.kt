package com.mesutemre.unittestwork.di

import com.mesutemre.unittestwork.data.local.ITarihteBugunLocalDataSource
import com.mesutemre.unittestwork.data.remote.ITarihteBugunRemoteDataSource
import com.mesutemre.unittestwork.data.repository.ITarihteBugunRepository
import com.mesutemre.unittestwork.domain.repository.TarihteBugunRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideTarihteBugunRepository(
        remoteDataSource: ITarihteBugunRemoteDataSource,
        localDataSource: ITarihteBugunLocalDataSource
    ): ITarihteBugunRepository {
        return TarihteBugunRepository(
            remoteDataSource, localDataSource
        )
    }
}