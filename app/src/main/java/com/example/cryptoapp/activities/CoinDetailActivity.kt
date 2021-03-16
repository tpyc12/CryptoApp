package com.example.cryptoapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityCoinDetailBinding
import com.example.cryptoapp.viewModel.CoinViewModel
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    private lateinit var vb: ActivityCoinDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        vb = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(vb.root)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)
        fromSymbol?.let {
            viewModel.getDetailInfo(it).observe(this, { coin ->
                vb.tvPriceDetail.text = coin.price.toString()
                vb.tvMinPrice.text = coin.lowDay.toString()
                vb.tvMaxPrice.text = coin.highDay.toString()
                vb.tvLastDeal.text = coin.lastMarket
                vb.tvLastUpdate.text = coin.getFormattedTime()
                vb.tvFSymb.text = coin.fromSymbol
                vb.tvTSymb.text = coin.toSymbol
                Picasso.get().load(coin.getFullImageUrl()).into(vb.ivLogo)
            })
        }
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}