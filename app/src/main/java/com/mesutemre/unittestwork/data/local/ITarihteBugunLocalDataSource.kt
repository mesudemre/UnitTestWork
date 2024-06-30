package com.mesutemre.unittestwork.data.local

import com.mesutemre.unittestwork.data.local.entity.TarihteBugunEntity

interface ITarihteBugunLocalDataSource {
    suspend fun saveTarihteBugunList(vararg tarihteBugunEntity: TarihteBugunEntity)
    suspend fun clearTarihteBugun()
    suspend fun getTarihteBugunList(): List<TarihteBugunEntity>
    suspend fun saveDateForTarihteBugun()
    suspend fun checkTarihteBugunCallAPI(): Boolean
}