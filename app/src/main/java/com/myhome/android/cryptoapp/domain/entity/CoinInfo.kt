package com.myhome.android.cryptoapp.domain.entity

data class CoinInfo(
    val fromSymbol: String,
    val toSymbol: String?,
    val price: String?,
    val highDay: String?,
    val lowDay: String?,
    val lastMarket: String?,
    val lastUpdate: String?,
    val imageUrl: String?
)