package com.android.example.coroutineapk.GameList


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GameListEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "short_description") val short_description: String,
    @ColumnInfo(name = "game_url") val game_url: String,
    @ColumnInfo(name = "genre") val genre: String,
    @ColumnInfo(name = "platform") val platform: String,
    @ColumnInfo(name = "publisher") val publisher: String,
    @ColumnInfo(name = "developer") val developer: String,
    @ColumnInfo(name = "release_date") val release_date: String,
    @ColumnInfo(name = "freetogame_profile_url") val freetogame_profile_url: String
)