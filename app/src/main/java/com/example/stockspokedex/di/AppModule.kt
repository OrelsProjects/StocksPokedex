package com.example.stockspokedex.di

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

    @Singleton
    @Provides
    fun provideMainViewState() = MainViewState()

    @Singleton
    @Provides
    fun provideLoginViewState() = LoginViewState()
}