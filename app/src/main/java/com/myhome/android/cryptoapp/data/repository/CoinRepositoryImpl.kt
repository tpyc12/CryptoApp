package com.myhome.android.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.myhome.android.cryptoapp.data.database.CoinInfoDao
import com.myhome.android.cryptoapp.data.mapper.CoinMapper
import com.myhome.android.cryptoapp.data.workers.RefreshDataWorker
import com.myhome.android.cryptoapp.domain.entity.CoinInfo
import com.myhome.android.cryptoapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val mapper: CoinMapper,
    private val coinInfoDao: CoinInfoDao,
    private val application: Application
) : CoinRepository {

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return Transformations.map(coinInfoDao.getPriceList()) {
            it.map {
                mapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(fSym: String): LiveData<CoinInfo> {
        return Transformations.map(coinInfoDao.getPriceInfoAboutCoin(fSym)) {
            mapper.mapDbModelToEntity(it)
        }
    }

    override suspend fun loadData() {
        val workerManager = WorkManager.getInstance(application)
        workerManager.enqueueUniqueWork(
            RefreshDataWorker.NAME,
            ExistingWorkPolicy.REPLACE,
            RefreshDataWorker.makeRequest()
        )
    }
}