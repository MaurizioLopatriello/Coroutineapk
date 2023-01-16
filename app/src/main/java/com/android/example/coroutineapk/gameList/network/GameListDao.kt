package com.android.example.coroutineapk.gameList.network

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.example.coroutineapk.gameList.GameListEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface GameListDao {
    @Query("Select * from GAME_LIST")
    fun getAll():Flow <List< GameListEntity>>

    @Insert
    fun insertAll(vararg gameList: List<GameListEntity>)
}