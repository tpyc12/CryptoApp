package com.myhome.android.cryptoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.myhome.android.cryptoapp.databinding.ActivityCoinPriceInfoBinding
import com.squareup.picasso.Picasso

class CoinPriceInfoActivity : AppCompatActivity() {

    private lateinit var ui: ActivityCoinPriceInfoBinding
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityCoinPriceInfoBinding.inflate(layoutInflater)
        setContentView(ui.root)

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol).observe(this) {
            ui.tvFromSymbol.text = it.fromSymbol
            ui.tvToSymbol.text = it.toSymbol
            ui.tvPrice.text = it.price.toString()
            ui.tvMinPrice.text = it.lowDay.toString()
            ui.tvMaxPrice.text = it.highDay.toString()
            ui.tvLastMarket.text = it.lastMarket
            ui.tvLastUpdate.text = it.getFormattedTime()
            Picasso.get().load(it.getFullImageUrl()).into(ui.ivLogoCoin)
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinPriceInfoActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}