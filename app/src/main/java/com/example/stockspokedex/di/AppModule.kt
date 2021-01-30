package com.example.stockspokedex.di

import com.example.stockspokedex.ui.fragments.AuthFragment
import com.example.stockspokedex.ui.fragments.SettingsFragment
import com.example.stockspokedex.ui.fragments.StockInfoFragment
import com.example.stockspokedex.ui.viewstates.AuthViewState
import com.example.stockspokedex.ui.viewstates.MainViewState
import com.example.stockspokedex.ui.viewstates.SettingsViewState
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

    // region ViewStates
    @Singleton
    @Provides
    fun provideMainViewState() = MainViewState()

    @Singleton
    @Provides
    fun provideLoginViewState() = AuthViewState()

    @Singleton
    @Provides
    fun provideStockInfoViewState() = StockInfoViewState()

    @Singleton
    @Provides
    fun provideSettingsViewState() = SettingsViewState()

    // endregion

    // region Fragments

    @Singleton
    @Provides
    fun provideLoginFragment() = AuthFragment()

    @Singleton
    @Provides
    fun provideStockInfoFragment() = StockInfoFragment()

    @Singleton
    @Provides
    fun provideSettingsFragment() = SettingsFragment()

    // endregion
}