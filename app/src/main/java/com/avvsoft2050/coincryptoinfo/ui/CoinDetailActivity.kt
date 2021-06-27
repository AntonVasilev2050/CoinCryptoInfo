package com.avvsoft2050.coincryptoinfo.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.avvsoft2050.coincryptoinfo.R
import com.avvsoft2050.coincryptoinfo.pojo.Coins
import com.avvsoft2050.coincryptoinfo.pojo.FavoriteCoins
import com.avvsoft2050.coincryptoinfo.ui.coins.CoinsViewModel
import com.avvsoft2050.coincryptoinfo.utils.Converter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_coin_detail.*
import kotlinx.android.synthetic.main.activity_coin_detail.tvFirstCurrentPriceD
import kotlinx.android.synthetic.main.item_coins_markets.*
import java.security.AccessController.getContext
import java.util.*
import kotlin.math.roundToInt

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var coinsViewModel: CoinsViewModel
    var currencyLabel = "$"
    lateinit var coins: Coins
    var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        if (!intent.hasExtra(EXTRA_SYMBOL)) {
            finish()
            return
        }
        val symbol = intent.getStringExtra(EXTRA_SYMBOL)
//        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(symbol?.uppercase())
        coinsViewModel = ViewModelProvider(this).get(CoinsViewModel::class.java)
        symbol?.let {
            coinsViewModel.getCoinInfo(it).observe(this, Observer {
                coins = it
//                val green = resources.getColor(android.R.color.holo_green_dark)
                val red = ContextCompat.getColor(this, android.R.color.holo_red_dark)
                val green = ContextCompat.getColor(this, android.R.color.holo_green_dark)
                Picasso.get().load(it.image).into(ivCoinIconD)
                tvNameD.text = it.name
                tvFirstCurrentPriceD.text = Converter.toUSCurrency(it.currentPrice)
                buttonBuyCoins.text = String.format("Купить %s", it.symbol)
                tvMinPrice.text = Converter.toUSCurrency(it.low24h)
                tvMaxPrice.text = Converter.toUSCurrency(it.high24h)
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
                tvMarketCap.text = Converter.toUSCurrency(it.marketCap?.toDouble())
                tvCirculatingSupply.text = Converter.toSplitNumber(it.symbol, it?.circulatingSupply)
                tvTotalSupply.text = Converter.toSplitNumber(it.symbol, it?.totalSupply)
                tvMaxSupply.text = Converter.toSplitNumber(it.symbol, it?.maxSupply)
                tvTotalVolume.text = Converter.toUSCurrency(it.totalVolume?.toDouble())
            })
            coinsViewModel.getFavoriteCoinsMarketsBySymbol(it).observe(this, {
                if (it != null){
                    isFavorite = true
                    ivFavoriteD.setImageResource(android.R.drawable.btn_star_big_on)
                }
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


    fun onClickSwitchFavorite(view: View) {
            if (isFavorite){
                coinsViewModel.deleteFavoriteCoins(FavoriteCoins(coins))
                ivFavoriteD.setImageResource(android.R.drawable.btn_star_big_off)
                isFavorite = false
                Toast.makeText(this, getString(R.string.remove_from_favorite), Toast.LENGTH_SHORT).show()
            }else{
                coinsViewModel.insertFavoriteCoins(FavoriteCoins(coins))
                ivFavoriteD.setImageResource(android.R.drawable.btn_star_big_on)
                isFavorite = true
                Toast.makeText(this, getString(R.string.add_to_favorite), Toast.LENGTH_SHORT).show()
            }
    }

}