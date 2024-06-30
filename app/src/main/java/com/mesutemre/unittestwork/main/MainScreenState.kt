package com.mesutemre.unittestwork.main

import com.mesutemre.unittestwork.core.BaseResourceEvent
import com.mesutemre.unittestwork.domain.data.TarihteBugunItem

data class MainScreenState(
    val tarihteBugunList: BaseResourceEvent<List<TarihteBugunItem>> = BaseResourceEvent.Loading()
)
