package com.mesutemre.unittestwork.api

import com.google.android.gms.common.util.IOUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mesutemre.namazvakitleri.dashboard.data.remote.dto.TarihteBugunDto
import com.mesutemre.unittestwork.coroutine.MainDispatcherRule
import com.mesutemre.unittestwork.data.remote.ITarihteBugunApi
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Buffer
import okio.Okio
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.InputStream
import java.io.InputStreamReader


class TarihteBugunApiTest {

    private lateinit var server: MockWebServer
    private lateinit var api:ITarihteBugunApi
    private lateinit var gson: Gson

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun createService() {
        gson = GsonBuilder().create()
        server = MockWebServer()
        api = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ITarihteBugunApi::class.java)
    }

    @Test
    fun `getTarihteBugun return Success`() = runTest {
        enqueueResponse()
        val data = api.getTarihteBugun().body()
        server.takeRequest()
        assertEquals(data?.success, true)
    }

    @Test
    fun `getTarihteBugun return Error`() = runTest {
        enqueueResponseWithError()
        val data = api.getTarihteBugun().body()
        server.takeRequest()
        assertEquals(data?.success, false)
    }

    @After
    fun stopService() {
        server.shutdown()
    }

    private fun enqueueResponse() {
        val mockResponse = MockResponse()
        mockResponse.setBody("{\"success\": true}")
        server.enqueue(mockResponse)
    }

    private fun enqueueResponseWithError() {
        val mockResponse = MockResponse()
        mockResponse.setBody("{\"success\": false}")
        server.enqueue(mockResponse)
    }
}