package com.mesutemre.unittestwork.data.remote

import com.mesutemre.namazvakitleri.dashboard.data.remote.dto.TarihteBugunDto
import retrofit2.Response
import javax.inject.Inject

class TarihteBugunRemoteDataSource @Inject constructor(
    private val api: ITarihteBugunApi
) : ITarihteBugunRemoteDataSource {

    override suspend fun getTarihteBugun(): Response<TarihteBugunDto> = api.getTarihteBugun()
}