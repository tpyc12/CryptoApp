package com.myhome.android.cryptoapp.domain

data class CoinInfo(
    val fromSymbol: String,
    val toSymbol: String,
    val price: Double,
    val lastUpdate: String,
    val highDay: Double,
    val lowDay: Double,
    val lastMarket: String,
    val imageUrl: String
)