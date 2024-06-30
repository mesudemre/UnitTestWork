package com.mesutemre.unittestwork.core

import android.accounts.NetworkErrorException
import com.mesutemre.unittestwork.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ServiceCall : IServiceCall {

    override fun <T : Any> serviceCall(call: suspend () -> Response<T>): Flow<BaseResourceEvent<T>> =
        flow {
            emit(BaseResourceEvent.Loading())
            var response: Response<T>? = null
            try {
                response = call()
            } catch (exception: Exception) {
                when (exception) {
                    is ConnectException -> {
                        emit(BaseResourceEvent.Error(message = exception.message!!))
                    }

                    is NetworkErrorException -> {
                        emit(BaseResourceEvent.Error(message = exception.message!!))
                    }

                    is SocketTimeoutException,
                    is UnknownHostException -> {
                        emit(
                            BaseResourceEvent.Error(
                                messageId = R.string.app_name,
                                message = null
                            )
                        )
                    }

                    else -> {
                        emit(BaseResourceEvent.Error(message = exception.message!!))
                    }
                }

            }
            if (response != null) {
                if (!response.isSuccessful) {
                    val errorBody = response.errorBody()
                    emit(BaseResourceEvent.Error(message = errorBody.toString()))
                } else {
                    emit(BaseResourceEvent.Success(response.body()!!))
                }
            }
        }
}