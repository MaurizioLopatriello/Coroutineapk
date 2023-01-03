package com.android.example.coroutineapk.GameList.Network

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.example.coroutineapk.GameList.GameListEntity
import java.util.concurrent.Flow


@Dao
interface GameListDao {
    @Query("Select * from GameListEntity")
   fun getAll():List<GameListEntity>

    @Insert
    fun insertAll(vararg gameListEntity :GameListEntity)
}