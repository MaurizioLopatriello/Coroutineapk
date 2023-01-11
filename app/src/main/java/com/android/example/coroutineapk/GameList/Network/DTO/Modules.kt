package com.android.example.coroutineapk.GameList.Network.DTO

import android.content.Context
import com.android.example.coroutineapk.GameList.Ui.UseCases.GameListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {androidContext().getSharedPreferences("This App", Context.MODE_PRIVATE  )}
}

val viewModels = module {
    viewModel{GameListViewModel(
        gameListProvider = get(),
        preferences = get(),
        gameListDao = gameListDao
    )}

}