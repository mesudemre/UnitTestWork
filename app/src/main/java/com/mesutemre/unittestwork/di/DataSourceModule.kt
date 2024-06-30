package com.mesutemre.unittestwork.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.mesutemre.unittestwork.data.local.ITarihteBugunDao
import com.mesutemre.unittestwork.data.local.ITarihteBugunLocalDataSource
import com.mesutemre.unittestwork.data.local.TarihteBugunLocalDataSource
import com.mesutemre.unittestwork.data.remote.ITarihteBugunApi
import com.mesutemre.unittestwork.data.remote.ITarihteBugunRemoteDataSource
import com.mesutemre.unittestwork.data.remote.TarihteBugunRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideTarihteBugunLocalDataSource(
        dao: ITarihteBugunDao, dataStore: DataStore<Preferences>
    ): ITarihteBugunLocalDataSource {
        return TarihteBugunLocalDataSource(
            dao, dataStore
        )
    }

    @Singleton
    @Provides
    fun provideTarihteBugunRemoteDataSource(
        api: ITarihteBugunApi
    ) : ITarihteBugunRemoteDataSource {
        return TarihteBugunRemoteDataSource(
            api = api
        )
    }
}