package com.myhome.android.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.myhome.android.cryptoapp.data.database.AppDatabase
import com.myhome.android.cryptoapp.data.mapper.CoinMapper
import com.myhome.android.cryptoapp.data.network.ApiFactory
import com.myhome.android.cryptoapp.data.workers.RefreshDataWorkerFactory
import com.myhome.android.cryptoapp.di.DaggerApplicationComponent

class CoinApp : Application(), Configuration.Provider {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(
                RefreshDataWorkerFactory(
                    AppDatabase.getInstance(this).coinPriceInfoDao(),
                    ApiFactory.apiService,
                    CoinMapper()
                )
            ).build()
    }

}