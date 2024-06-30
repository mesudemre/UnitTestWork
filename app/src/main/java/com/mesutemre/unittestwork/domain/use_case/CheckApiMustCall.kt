package com.mesutemre.unittestwork.domain.use_case

import com.mesutemre.unittestwork.data.repository.ITarihteBugunRepository
import javax.inject.Inject

class CheckApiMustCall @Inject constructor(
    private val tarihteBugunRepository: ITarihteBugunRepository
) {
    suspend operator fun invoke() : Boolean = tarihteBugunRepository.checkTarihteBugunCallAPI()
}