package com.myhome.android.cryptoapp.domain.usecases

import com.myhome.android.cryptoapp.domain.repository.CoinRepository

class GetCoinInfoUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke(fSym: String) = repository.getCoinInfo(fSym)
}