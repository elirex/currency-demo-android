package com.elirex.currencydemo.di

import android.content.Context
import com.elirex.currencydemo.data.CurrencyRepository
import com.elirex.currencydemo.data.CurrencyRepositoryImpl
import com.elirex.currencydemo.data.source.DataSource
import com.elirex.currencydemo.data.source.local.LocalDataSource
import com.elirex.currencydemo.data.source.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideCurrencyRepository(appDatabase: AppDatabase): CurrencyRepository {
        return CurrencyRepositoryImpl(LocalDataSource(appDatabase.currencyDao()))
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.buildDatabase(context)
    }
}