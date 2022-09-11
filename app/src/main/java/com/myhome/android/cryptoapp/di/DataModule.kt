package com.myhome.android.cryptoapp.di

import android.app.Application
import com.myhome.android.cryptoapp.data.database.AppDatabase
import com.myhome.android.cryptoapp.data.database.CoinInfoDao
import com.myhome.android.cryptoapp.data.repository.CoinRepositoryImpl
import com.myhome.android.cryptoapp.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindCoinRepository(impl: CoinRepositoryImpl): CoinRepository

    companion object {

        @Provides
        fun provideCoinInfoDao(
            application: Application
        ): CoinInfoDao {
            return AppDatabase.getInstance(application).coinPriceInfoDao()
        }
    }
}