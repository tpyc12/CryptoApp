package com.myhome.android.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.myhome.android.cryptoapp.R
import com.myhome.android.cryptoapp.databinding.ItemCoinInfoBinding
import com.myhome.android.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) :
    ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffCallback) {

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        with(holder.binding) {
            with(coin) {
                val symbolsTemplate = context.resources.getString(R.string.symbols_template)
                val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
                tvCoinName.text = String.format(symbolsTemplate, fromSymbol, toSymbol)
                tvCoinPrice.text = price.toString()
                tvLastUpdate.text = String.format(lastUpdateTemplate, lastUpdate)
                Picasso.get().load(imageUrl).into(ivCoinImage)
                root.setOnClickListener {
                    onCoinClickListener?.onCoinClick(coin)
                }
            }
        }
    }

    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinInfo)
    }
}