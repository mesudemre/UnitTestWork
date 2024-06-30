package com.mesutemre.unittestwork.domain.use_case

import com.mesutemre.unittestwork.data.repository.ITarihteBugunRepository
import com.mesutemre.unittestwork.domain.data.TarihteBugunItem
import javax.inject.Inject

class SaveTarihteBugunListToDb @Inject constructor(
    private val tarihteBugunRepository: ITarihteBugunRepository
) {
    suspend operator fun invoke(list: List<TarihteBugunItem>) {
        tarihteBugunRepository.saveTarihteBugunListToDb(list)
    }
}