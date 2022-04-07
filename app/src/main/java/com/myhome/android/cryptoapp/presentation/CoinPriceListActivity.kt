package com.myhome.android.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.myhome.android.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.myhome.android.cryptoapp.domain.CoinInfo
import com.myhome.android.cryptoapp.presentation.adapters.CoinInfoAdapter

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var ui: ActivityCoinPriceListBinding
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(ui.root)

        val adapter = CoinInfoAdapter(this)
        ui.rvCoinPriceList.adapter = adapter
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                val intent = CoinPriceInfoActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }
        }

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.coinInfoList = it
        }
    }
}

