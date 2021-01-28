package com.example.stockspokedex.di

import com.example.stockspokedex.ui.fragments.LoginFragment
import com.example.stockspokedex.ui.fragments.StockInfoFragment
import com.example.stockspokedex.ui.viewstates.LoginViewState
import com.example.stockspokedex.ui.viewstates.MainViewState
import com.example.stockspokedex.ui.viewstates.StockInfoViewState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    // todo provide the viewmodels

    @Singleton
    @Provides
    fun provideMainViewState() = MainViewState()

    @Singleton
    @Provides
    fun provideLoginViewState() = LoginViewState()

    @Singleton
    @Provides
    fun provideStockInfoViewState() = StockInfoViewState()

    @Singleton
    @Provides
    fun provideLoginFragment() = LoginFragment()

    @Singleton
    @Provides
    fun provideStockInfoFragment() = StockInfoFragment()
}