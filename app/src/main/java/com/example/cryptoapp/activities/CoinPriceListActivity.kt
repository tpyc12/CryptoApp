package com.example.cryptoapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.adapters.CoinInfoAdapter
import com.example.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.example.cryptoapp.pojo.CoinPriceInfo
import com.example.cryptoapp.viewModel.CoinViewModel

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    private lateinit var viewBinding: ActivityCoinPriceListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        viewBinding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }
        }
        viewBinding.rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this).get(CoinViewModel::class.java)
        viewModel.priceList.observe(this, {
            adapter.coinInfoList = it
        })
    }
}