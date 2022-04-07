package com.myhome.android.cryptoapp.domain

class GetCoinInfoUseCase(
    private val repository: CoinRepository
) {

    operator fun invoke(fromSymbol: String) = repository.getCoinInfo(fromSymbol)
}