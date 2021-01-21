package com.example.stockspokedex.di

import com.example.stockspokedex.ui.fragments.AddStockFragment
import com.example.stockspokedex.ui.fragments.LoginFragment
import com.example.stockspokedex.ui.viewstates.AddStockViewState
import com.example.stockspokedex.ui.viewstates.LoginViewState
import com.example.stockspokedex.ui.viewstates.MainViewState
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
    fun provideAddStockViewState() = AddStockViewState()

    @Singleton
    @Provides
    fun provideLoginFragment() = LoginFragment()

    @Singleton
    @Provides
    fun provideAddStockFragment() = AddStockFragment()
}