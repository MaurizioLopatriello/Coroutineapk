package com.android.example.coroutineapk.GameList.Network.DTO

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.example.coroutineapk.GameList.GameListEntity
import com.android.example.coroutineapk.GameList.Network.GameListDao

@Database(entities = [GameListEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getGamesDao(): GameListDao
}