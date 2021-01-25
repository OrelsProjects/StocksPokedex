package com.example.stockspokedex.di

import android.content.Context
import androidx.room.Room
import com.example.stockspokedex.R
import com.example.stockspokedex.data.daos.*
import com.example.stockspokedex.data.database.LocalDatabase
import com.example.stockspokedex.interactors.ChecklistInteractorImpl
import com.example.stockspokedex.interactors.CompanyInteractorImpl
import com.example.stockspokedex.interactors.StockInteractorImpl
import com.example.stockspokedex.interactors.UserInteractorImpl
import com.example.stockspokedex.models.ChecklistInteractor
import com.example.stockspokedex.models.CompanyInteractor
import com.example.stockspokedex.models.StockInteractor
import com.example.stockspokedex.models.UserInteractor
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

    // region Interactors

    @Singleton
    @Provides
    fun provideUserInteractor(interactorImpl: UserInteractorImpl): UserInteractor = interactorImpl

    @Singleton
    @Provides
    fun provideCompanyInteractor(interactorImpl: CompanyInteractorImpl): CompanyInteractor =
        interactorImpl

    @Singleton
    @Provides
    fun provideChecklistInteractor(interactorImpl: ChecklistInteractorImpl): ChecklistInteractor =
        interactorImpl

    @Singleton
    @Provides
    fun provideStockInteractor(interactorImpl: StockInteractorImpl): StockInteractor =
        interactorImpl

    // endregion

    //region dbs

    @Singleton
    @Provides
    fun provideCompanyDB(@ApplicationContext context: Context): CompanyDao = Room.databaseBuilder(
        context,
        LocalDatabase::class.java, context.getString(R.string.local_db_name)
    ).fallbackToDestructiveMigration().allowMainThreadQueries().build().companyDao()

    @Singleton
    @Provides
    fun provideChecklistDB(@ApplicationContext context: Context): ChecklistDao =
        Room.databaseBuilder(
            context,
            LocalDatabase::class.java, context.getString(R.string.local_db_name)
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build().checklistDao()

    @Singleton
    @Provides
    fun provideFileDB(@ApplicationContext context: Context): FileDao = Room.databaseBuilder(
        context,
        LocalDatabase::class.java, context.getString(R.string.local_db_name)
    ).fallbackToDestructiveMigration().allowMainThreadQueries().build().fileDao()

    @Singleton
    @Provides
    fun provideUserDB(@ApplicationContext context: Context): UserDao = Room.databaseBuilder(
        context,
        LocalDatabase::class.java, context.getString(R.string.local_db_name)
    ).fallbackToDestructiveMigration().allowMainThreadQueries().build().userDao()

    @Singleton
    @Provides
    fun provideStockDB(@ApplicationContext context: Context): StockDao = Room.databaseBuilder(
        context,
        LocalDatabase::class.java, context.getString(R.string.local_db_name)
    ).fallbackToDestructiveMigration().allowMainThreadQueries().build().stockDao()

    // endregion

    // region firebase

    @Singleton
    @Provides
    fun provideFirebaseStorageRef(): StorageReference = FirebaseStorage.getInstance().reference

    @Singleton
    @Provides
    fun provideFirebaseRef(): FirebaseFirestore = FirebaseFirestore.getInstance()

    // endregion
}