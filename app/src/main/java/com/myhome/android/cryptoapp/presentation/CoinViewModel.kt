package com.myhome.android.cryptoapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myhome.android.cryptoapp.domain.usecases.GetCoinInfoListUseCase
import com.myhome.android.cryptoapp.domain.usecases.GetCoinInfoUseCase
import com.myhome.android.cryptoapp.domain.usecases.LoadDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase()

    init {
        viewModelScope.launch {
            loadDataUseCase()
        }
    }

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase(fSym)
}