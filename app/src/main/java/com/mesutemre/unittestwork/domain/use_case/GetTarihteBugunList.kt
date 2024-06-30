package com.mesutemre.unittestwork.domain.use_case

import com.mesutemre.namazvakitleri.di.IoDispatcher
import com.mesutemre.unittestwork.core.BaseResourceEvent
import com.mesutemre.unittestwork.domain.data.TarihteBugunItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetTarihteBugunList @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val getTarihteBugunListFromApi: GetTarihteBugunListFromApi,
    private val getTarihteBugunListFromDb: GetTarihteBugunListFromDb,
    private val checkApiMustCall: CheckApiMustCall
) {
    suspend operator fun invoke(): Flow<BaseResourceEvent<List<TarihteBugunItem>>> {
        return if (checkApiMustCall()) {
            getTarihteBugunListFromApi()
        } else {
            getTarihteBugunListFromDb()
        }.flowOn(ioDispatcher)
    }
}