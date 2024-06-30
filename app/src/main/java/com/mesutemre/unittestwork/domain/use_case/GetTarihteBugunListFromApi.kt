package com.mesutemre.unittestwork.domain.use_case

import com.mesutemre.unittestwork.core.BaseResourceEvent
import com.mesutemre.unittestwork.data.repository.ITarihteBugunRepository
import com.mesutemre.unittestwork.domain.data.TarihteBugunItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class GetTarihteBugunListFromApi @Inject constructor(
    private val tarihteBugunRepository: ITarihteBugunRepository,
    private val saveTarihteBugunListToDb: SaveTarihteBugunListToDb,
    private val saveTarihteBugunTarihToDataStore: SaveTarihteBugunTarihToDataStore
) {
    suspend operator fun invoke(): Flow<BaseResourceEvent<List<TarihteBugunItem>>> {
        val response = tarihteBugunRepository.getTarihteBugunListFromAPI()
        response.collectLatest { res ->
            if (res is BaseResourceEvent.Success) {
                res.data?.let { list ->
                    saveTarihteBugunListToDb(list)
                    saveTarihteBugunTarihToDataStore()
                }
            }
        }
       return response
    }
}