package com.myhome.android.cryptoapp.domain.usecases

import com.myhome.android.cryptoapp.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinInfoUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(fSym: String) = repository.getCoinInfo(fSym)
}