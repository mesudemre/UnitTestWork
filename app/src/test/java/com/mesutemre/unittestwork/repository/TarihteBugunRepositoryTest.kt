package com.mesutemre.unittestwork.repository

import com.mesutemre.unittestwork.R
import com.mesutemre.unittestwork.core.BaseResourceEvent
import com.mesutemre.unittestwork.data.local.entity.TarihteBugunEntity
import com.mesutemre.unittestwork.data.repository.ITarihteBugunRepository
import com.mesutemre.unittestwork.domain.data.TarihteBugunItem
import com.mesutemre.unittestwork.main.MainScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class TarihteBugunRepositoryTest : ITarihteBugunRepository {

    val tarihteBugunList = mutableListOf<TarihteBugunItem>(
            TarihteBugunItem(
                itemId = 1,
                tarih = "26.01.1991",
                olay = "Doğum",
                durum = "Mesut Emre Çelenk dünyaya geldi"
            ),
            TarihteBugunItem(
                itemId = 2,
                tarih = "07.03.1992",
                olay = "Doğum",
                durum = "Büşra Çelenk dünyaya geldi"
            ),
            TarihteBugunItem(
                itemId = 3,
                tarih = "04.08.2019",
                olay = "Doğum",
                durum = "Zeynep Ülkü Çelenk dünyaya geldi"
            )
    )


    override suspend fun saveTarihteBugunTarih() {

    }

    override suspend fun checkTarihteBugunCallAPI(): Boolean {
       return false
    }

    override suspend fun getTarihteBugunListFromAPI(): Flow<BaseResourceEvent<List<TarihteBugunItem>>> =
        flow {
            emit(BaseResourceEvent.Loading())
            delay(1000)
            emit(BaseResourceEvent.Success(data = this@TarihteBugunRepositoryTest.tarihteBugunList))
            //emit(BaseResourceEvent.Error(data = null, messageId = null, message = "Datalar alınamadı"))
        }

    override suspend fun saveTarihteBugunListToDb(tarihteBugunList: List<TarihteBugunItem>) {

    }

    override suspend fun getTarihteBugunListFromDB(): Flow<BaseResourceEvent<List<TarihteBugunItem>>> =
        flow {
            emit(BaseResourceEvent.Loading())
            delay(1000)
            emit(BaseResourceEvent.Success(data = this@TarihteBugunRepositoryTest.tarihteBugunList))
            //emit(BaseResourceEvent.Error(data = null, messageId = null, message = "Datalar alınamadı"))
        }
}