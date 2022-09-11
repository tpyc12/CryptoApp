package com.myhome.android.cryptoapp.domain.usecases

import com.myhome.android.cryptoapp.domain.repository.CoinRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    suspend operator fun invoke() {
        repository.loadData()
    }
}