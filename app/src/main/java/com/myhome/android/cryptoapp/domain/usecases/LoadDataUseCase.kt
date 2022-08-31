package com.myhome.android.cryptoapp.domain.usecases

import com.myhome.android.cryptoapp.domain.repository.CoinRepository

class LoadDataUseCase(
    private val repository: CoinRepository
) {

    suspend operator fun invoke() {
        repository.loadData()
    }
}