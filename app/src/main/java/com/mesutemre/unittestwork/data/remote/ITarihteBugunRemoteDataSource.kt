package com.mesutemre.unittestwork.data.remote

import com.mesutemre.namazvakitleri.dashboard.data.remote.dto.TarihteBugunDto
import retrofit2.Response

interface ITarihteBugunRemoteDataSource {

    suspend fun getTarihteBugun(): Response<TarihteBugunDto>
}