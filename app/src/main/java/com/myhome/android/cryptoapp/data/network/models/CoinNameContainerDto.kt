package com.myhome.android.cryptoapp.data.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CoinNameContainerDto(
    @SerializedName("CoinInfo")
    @Expose
    val coinName: CoinNameDto
)