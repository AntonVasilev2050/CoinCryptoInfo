package com.avvsoft2050.coincryptoinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.avvsoft2050.coincryptoinfo.ui.main.CoinsMarketsViewModel

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var coinsMarketsViewModel: CoinsMarketsViewModel

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
            })
        }
    }

    companion object {
        private const val EXTRA_SYMBOL = "symbol"

        fun newIntent(context: FragmentActivity?, symbol: String): Intent{
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_SYMBOL, symbol)
            return intent
        }
    }
}