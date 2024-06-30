package com.mesutemre.unittestwork.domain.repository

import com.mesutemre.namazvakitleri.di.IoDispatcher
import com.mesutemre.unittestwork.R
import com.mesutemre.unittestwork.core.BaseResourceEvent
import com.mesutemre.unittestwork.data.local.ITarihteBugunLocalDataSource
import com.mesutemre.unittestwork.data.local.entity.TarihteBugunEntity
import com.mesutemre.unittestwork.data.remote.ITarihteBugunRemoteDataSource
import com.mesutemre.unittestwork.data.repository.ITarihteBugunRepository
import com.mesutemre.unittestwork.domain.data.TarihteBugunItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TarihteBugunRepository @Inject constructor(
    private val remoteDataSource: ITarihteBugunRemoteDataSource,
    private val localDataSource: ITarihteBugunLocalDataSource
) : ITarihteBugunRepository {

    override suspend fun saveTarihteBugunTarih() {
        localDataSource.saveDateForTarihteBugun()
    }

    override suspend fun checkTarihteBugunCallAPI(): Boolean =
        localDataSource.checkTarihteBugunCallAPI()

    override suspend fun getTarihteBugunListFromAPI(): Flow<BaseResourceEvent<List<TarihteBugunItem>>> =
        flow {
            emit(BaseResourceEvent.Loading())
            val res = remoteDataSource.getTarihteBugun()
            if (res.isSuccessful) {
                res.body()?.let { body ->
                    if (body.success) {
                        if (body.itemList.isNullOrEmpty()) {
                            emit(
                                BaseResourceEvent.Error(
                                    messageId = R.string.common_empty_data,
                                    message = null
                                )
                            )
                        } else {
                            emit(BaseResourceEvent.Success(
                                data = body.itemList.mapIndexed { index, item ->
                                    TarihteBugunItem(
                                        itemId = index,
                                        tarih = item.gun + "." + item.ay + "." + item.yil,
                                        durum = item.durum,
                                        olay = item.olay
                                    )
                                }
                            ))
                        }
                    } else {
                        emit(
                            BaseResourceEvent.Error(
                                messageId = R.string.service_error,
                                message = null
                            )
                        )
                    }
                }
            } else {
                emit(
                    BaseResourceEvent.Error(
                        message = res.message(),
                    )
                )
            }
        }

    override suspend fun saveTarihteBugunListToDb(tarihteBugunList: List<TarihteBugunItem>) {
        localDataSource.saveTarihteBugunList(
            *tarihteBugunList.map { item->
                TarihteBugunEntity(
                    itemId = item.itemId,
                    durum = item.durum,
                    olay = item.olay,
                    tarih = item.tarih
                )
            }.toTypedArray()
        )
    }

    override suspend fun getTarihteBugunListFromDB(): Flow<BaseResourceEvent<List<TarihteBugunItem>>> =
        flow {
            emit(BaseResourceEvent.Loading())
            val list = localDataSource.getTarihteBugunList()
            if (list.isNullOrEmpty()) {
                emit(
                    BaseResourceEvent.Error(
                        messageId = R.string.common_empty_data,
                        message = null
                    )
                )
            } else {
                emit(BaseResourceEvent.Success(data = list.map {
                    TarihteBugunItem(
                        itemId = it.itemId ?: 0,
                        olay = it.olay,
                        durum = it.durum,
                        tarih = it.tarih
                    )
                }))
            }
        }

}