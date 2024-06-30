package com.mesutemre.unittestwork.domain.use_case

import com.mesutemre.unittestwork.core.BaseResourceEvent
import com.mesutemre.unittestwork.data.repository.ITarihteBugunRepository
import com.mesutemre.unittestwork.domain.data.TarihteBugunItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTarihteBugunListFromDb @Inject constructor(
    private val tarihteBugunRepository: ITarihteBugunRepository
) {
    suspend operator fun invoke(): Flow<BaseResourceEvent<List<TarihteBugunItem>>> {
        return tarihteBugunRepository.getTarihteBugunListFromDB()
    }
}