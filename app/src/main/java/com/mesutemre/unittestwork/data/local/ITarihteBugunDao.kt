package com.mesutemre.unittestwork.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mesutemre.unittestwork.data.local.entity.TarihteBugunEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ITarihteBugunDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTarihteBugunList(vararg tarihteBugunEntity: TarihteBugunEntity)

    @Query("DELETE FROM TarihteBugunEntity")
    suspend fun clearTarihteBugun()

    @Query("SELECT * FROM TarihteBugunEntity")
    suspend fun getTarihteBugunList(): List<TarihteBugunEntity>
}