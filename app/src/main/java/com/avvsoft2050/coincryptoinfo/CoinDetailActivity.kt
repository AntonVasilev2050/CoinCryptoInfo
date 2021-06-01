package com.avvsoft2050.coincryptoinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.avvsoft2050.coincryptoinfo.ui.main.CoinsMarketsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_coin_detail.*
import kotlinx.android.synthetic.main.activity_coin_detail.tvFirstCurrentPriceD
import kotlinx.android.synthetic.main.item_coins_markets.*
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var coinsMarketsViewModel: CoinsMarketsViewModel
    var currencyLabel = "$"
    var isClicked = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if (!intent.hasExtra(EXTRA_SYMBOL)) {
            finish()
            return
        }
        val symbol = intent.getStringExtra(EXTRA_SYMBOL)
        coinsMarketsViewModel = ViewModelProvider(this).get(CoinsMarketsViewModel::class.java)
        symbol?.let {
            coinsMarketsViewModel.getCoinInfo(it).observe(this, Observer {
                Log.d("DETAIL_INFO", it.toString())
                val red = resources.getColor(android.R.color.holo_red_light)
                val green = resources.getColor(android.R.color.holo_green_light)
                Picasso.get().load(it.image).into(ivCoinIconD)
                tvNameD.text = it.name
                tvFirstCurrentPriceD.text = it.currentPrice.toString()
                tvMinPrice.text = "$currencyLabel${it.low24h}"
                tvMaxPrice.text = "$currencyLabel${it.high24h}"
                it.priceChangePercentage1hInCurrency?.let {
                    val changeHour = (it * 100).roundToInt() / 100.0
                    if (it < 0) {
                        tvPercentage1Hour.setTextColor(red)
                    } else {
                        tvPercentage1Hour.setTextColor(green)
                    }
                    tvPercentage1Hour.text = changeHour.toString()
                }
                it.priceChangePercentage24hInCurrency?.let {
                    val change24Hours = (it * 100).roundToInt() / 100.0
                    if (it < 0) {
                        tvPercentage24Hour.setTextColor(red)
                    } else {
                        tvPercentage24Hour.setTextColor(green)
                    }
                    tvPercentage24Hour.text = change24Hours.toString()
                }
                it.priceChangePercentage7dInCurrency?.let {
                    val change7Days = (it * 100).roundToInt() / 100.0
                    if (it < 0) {
                        tvPercentage7Days.setTextColor(red)
                    } else {
                        tvPercentage7Days.setTextColor(green)
                    }
                    tvPercentage7Days.text = change7Days.toString()
                }
                tvMarketCap.text = it.marketCap.toString()
                tvCirculatingSupply.text =
                    it.circulatingSupply?.roundToLong()?.toString() ?: "нет данных"
                tvTotalSupply.text = it.totalSupply?.roundToLong()?.toString() ?: "нет данных"
                tvMaxSupply.text = it.maxSupply?.roundToLong()?.toString() ?: "нет данных"
                tvTotalVolume.text = it.totalVolume?.toString() ?: "нет данных"
            })
        }
    }

    companion object {
        private const val EXTRA_SYMBOL = "symbol"

        fun newIntent(context: FragmentActivity?, symbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_SYMBOL, symbol)
            return intent
        }
    }
}