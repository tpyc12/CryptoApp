package com.myhome.android.cryptoapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.myhome.android.cryptoapp.R
import com.myhome.android.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.myhome.android.cryptoapp.domain.CoinInfo
import com.myhome.android.cryptoapp.presentation.adapters.CoinInfoAdapter

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinPriceListBinding
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = CoinInfoAdapter(this)
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinInfo) {
                if (isOnePane()) {
                    launchCoinPriceListActivity(coinPriceInfo.fromSymbol)
                } else {
                    launchCoinPriceListFragment(coinPriceInfo.fromSymbol)
                }
            }
        }

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun isOnePane() = binding.fragmentContainer == null

    private fun launchCoinPriceListActivity(fromSymbol: String) {
        val intent = CoinPriceInfoActivity.newIntent(
            this@CoinPriceListActivity,
            fromSymbol
        )
        startActivity(intent)
    }

    private fun launchCoinPriceListFragment(fromSymbol: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, CoinPriceInfoFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }
}

