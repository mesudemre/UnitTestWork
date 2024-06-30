package com.mesutemre.unittestwork.viewmodel

import app.cash.turbine.test
import com.mesutemre.unittestwork.core.BaseResourceEvent
import com.mesutemre.unittestwork.coroutine.MainDispatcherRule
import com.mesutemre.unittestwork.domain.data.TarihteBugunItem
import com.mesutemre.unittestwork.domain.use_case.CheckApiMustCall
import com.mesutemre.unittestwork.domain.use_case.GetTarihteBugunList
import com.mesutemre.unittestwork.domain.use_case.GetTarihteBugunListFromApi
import com.mesutemre.unittestwork.domain.use_case.GetTarihteBugunListFromDb
import com.mesutemre.unittestwork.domain.use_case.SaveTarihteBugunListToDb
import com.mesutemre.unittestwork.domain.use_case.SaveTarihteBugunTarihToDataStore
import com.mesutemre.unittestwork.main.MainScreenViewModel
import com.mesutemre.unittestwork.repository.TarihteBugunRepositoryTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainScreenViewModelTest {

    private lateinit var mainScreenViewModel: MainScreenViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var getTarihteBugunList: GetTarihteBugunList
    private lateinit var getTarihteBugunListFromApi: GetTarihteBugunListFromApi
    private lateinit var getTarihteBugunListFromDb: GetTarihteBugunListFromDb
    private lateinit var checkApiMustCall: CheckApiMustCall
    private lateinit var saveTarihteBugunListToDb: SaveTarihteBugunListToDb
    private lateinit var saveTarihteBugunTarihToDataStore: SaveTarihteBugunTarihToDataStore

    @Before
    fun setUp() {
        checkApiMustCall = CheckApiMustCall(
            TarihteBugunRepositoryTest()
        )
        saveTarihteBugunListToDb = SaveTarihteBugunListToDb(
            TarihteBugunRepositoryTest()
        )
        saveTarihteBugunTarihToDataStore = SaveTarihteBugunTarihToDataStore(
            TarihteBugunRepositoryTest()
        )
        getTarihteBugunListFromDb = GetTarihteBugunListFromDb(TarihteBugunRepositoryTest())
        getTarihteBugunListFromApi = GetTarihteBugunListFromApi(
            TarihteBugunRepositoryTest(),
            saveTarihteBugunListToDb,
            saveTarihteBugunTarihToDataStore

        )
        getTarihteBugunList = GetTarihteBugunList(
            ioDispatcher = mainDispatcherRule.testDispatcher,
            getTarihteBugunListFromApi = getTarihteBugunListFromApi,
            getTarihteBugunListFromDb = getTarihteBugunListFromDb,
            checkApiMustCall = checkApiMustCall
        )
        mainScreenViewModel = MainScreenViewModel(
            getTarihteBugunList
        )
    }


    @Test
    fun `tarihte bugun listesini apiden success donecek sekilde getir`() = runTest {
        mainScreenViewModel.getTarihteBugunListFromSource().test {
            awaitItem()
            val res = awaitItem()
            val expected = BaseResourceEvent.Success<List<TarihteBugunItem>>(
                data = TarihteBugunRepositoryTest().tarihteBugunList
            )
            assertEquals(expected.data, res.data)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `tarihte bugun listesini apiden error donecek sekilde getir`() = runTest {
        mainScreenViewModel.getTarihteBugunListFromSource().test {
            awaitItem()
            val res = awaitItem()
            val expected = BaseResourceEvent.Error(
                data = null,
                message = "",
                messageId = null
            )
            assertEquals(expected.data, res.data)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `tarihte bugun listesi apiden mi geliyor`() = runTest {
        assertEquals(checkApiMustCall(),false)
    }

    @Test
    fun `tarihte bugun listesi cachelenmis mi`() = runTest {
        getTarihteBugunListFromDb().test {
            awaitItem()
            val res = awaitItem()
            val expected = BaseResourceEvent.Success<List<TarihteBugunItem>>(
                data = TarihteBugunRepositoryTest().tarihteBugunList
            )
            assertEquals(expected.data, res.data)
            cancelAndIgnoreRemainingEvents()
        }
    }
}