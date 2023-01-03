package com.android.example.coroutineapk.GameList

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity
 data class GameListEntity (
     @PrimaryKey val id : Int,
     @ColumnInfo(name = "first_name")val name : String,
     @ColumnInfo(name="last_name")val surname: String


)