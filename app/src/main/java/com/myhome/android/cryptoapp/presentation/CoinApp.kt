package com.myhome.android.cryptoapp.presentation

import android.app.Application
import com.myhome.android.cryptoapp.di.DaggerApplicationComponent

class CoinApp : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}