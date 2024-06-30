package com.mesutemre.unittestwork.di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mesutemre.unittestwork.data.local.ITarihteBugunDao
import com.mesutemre.unittestwork.data.local.entity.TarihteBugunEntity

@Database(
    version = 1,
    entities = [TarihteBugunEntity::class],
)
abstract class UnitTestWorkDatabase : RoomDatabase() {

    abstract fun getTarihteBugunDao(): ITarihteBugunDao
}