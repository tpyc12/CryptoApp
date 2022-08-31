package com.myhome.android.cryptoapp.domain.usecases

import com.myhome.android.cryptoapp.domain.repository.CoinRepository

class GetCoinInfoListUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke() = repository.getCoinInfoList()
}