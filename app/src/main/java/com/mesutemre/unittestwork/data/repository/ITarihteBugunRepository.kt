package com.mesutemre.unittestwork.data.repository

import com.mesutemre.namazvakitleri.dashboard.data.remote.dto.TarihteBugunDto
import com.mesutemre.unittestwork.core.BaseResourceEvent
import com.mesutemre.unittestwork.data.local.entity.TarihteBugunEntity
import com.mesutemre.unittestwork.domain.data.TarihteBugunItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ITarihteBugunRepository {
    suspend fun saveTarihteBugunTarih()
    suspend fun checkTarihteBugunCallAPI(): Boolean
    suspend fun getTarihteBugunListFromAPI(): Flow<BaseResourceEvent<List<TarihteBugunItem>>>
    suspend fun saveTarihteBugunListToDb(tarihteBugunList: List<TarihteBugunItem>)
    suspend fun getTarihteBugunListFromDB(): Flow<BaseResourceEvent<List<TarihteBugunItem>>>
}