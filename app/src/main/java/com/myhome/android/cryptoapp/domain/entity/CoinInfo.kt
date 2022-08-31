package com.myhome.android.cryptoapp.domain.entity

data class CoinInfo(
    val fromSymbol: String,
    val toSymbol: String? = null,
    val price: Double? = null,
    val highDay: Double? = null,
    val lowDay: Double? = null,
    val lastMarket: String? = null,
    val lastUpdate: Long? = null,
    val imageUrl: String? = null
)