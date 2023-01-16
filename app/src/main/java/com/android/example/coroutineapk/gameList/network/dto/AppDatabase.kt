package com.android.example.coroutineapk.gameList.network.dto

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.example.coroutineapk.gameList.GameListEntity
import com.android.example.coroutineapk.gameList.network.GameListDao

@Database(entities = [GameListEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getGamesDao(): GameListDao
}