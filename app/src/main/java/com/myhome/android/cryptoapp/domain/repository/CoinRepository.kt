package com.myhome.android.cryptoapp.domain.repository

import androidx.lifecycle.LiveData
import com.myhome.android.cryptoapp.domain.entity.CoinInfo

interface CoinRepository {

    fun getCoinInfoList(): LiveData<List<CoinInfo>>

    fun getCoinInfo(fSym: String): LiveData<CoinInfo>
}