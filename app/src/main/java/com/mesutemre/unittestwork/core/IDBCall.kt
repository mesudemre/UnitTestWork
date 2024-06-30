package com.mesutemre.unittestwork.core

import kotlinx.coroutines.flow.Flow

interface IDBCall {

    fun <T : Any> dbCall(
        call: suspend () -> T
    ): Flow<BaseResourceEvent<T>>
}