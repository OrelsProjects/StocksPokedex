package com.example.stockspokedex.di

import com.example.stockspokedex.ui.fragments.AuthFragment
import com.example.stockspokedex.ui.fragments.LoadingFragment
import com.example.stockspokedex.ui.fragments.SettingsFragment
import com.example.stockspokedex.ui.fragments.StockInfoFragment
import com.example.stockspokedex.ui.viewstates.*
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

    @Singleton
    @Provides
    fun provideLoadingViewState() = LoadingViewState()

    // endregion

    // region Fragments

    @Provides
    fun provideLoginFragment() = AuthFragment()

    @Provides
    fun provideStockInfoFragment() = StockInfoFragment()

    @Provides
    fun provideSettingsFragment() = SettingsFragment()

    @Provides
    fun provideLoadingFragment() = LoadingFragment()

    // endregion
}