package com.mesutemre.unittestwork.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.mesutemre.unittestwork.data.local.entity.TarihteBugunEntity
import com.mesutemre.unittestwork.ext.readString
import com.mesutemre.unittestwork.ext.saveData
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class TarihteBugunLocalDataSource @Inject constructor(
    private val dao: ITarihteBugunDao,
    private val dataStore: DataStore<Preferences>,
) : ITarihteBugunLocalDataSource {

    override suspend fun saveTarihteBugunList(vararg tarihteBugunEntity: TarihteBugunEntity) {
        dao.saveTarihteBugunList(*tarihteBugunEntity)
    }

    override suspend fun clearTarihteBugun() {
        dao.clearTarihteBugun()
    }

    override suspend fun getTarihteBugunList(): List<TarihteBugunEntity> = dao.getTarihteBugunList()

    override suspend fun saveDateForTarihteBugun() {
        dataStore.saveData(
            "TARIHTE_BUGUN_KEY",
            SimpleDateFormat("dd.MM.yyyy").format(Date())
        )
    }

    override suspend fun checkTarihteBugunCallAPI(): Boolean {
        return (dataStore.readString("TARIHTE_BUGUN_KEY", "")
            .first() == SimpleDateFormat("dd.MM.yyyy").format(Date())).not()
    }
}