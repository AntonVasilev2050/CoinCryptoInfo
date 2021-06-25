package com.avvsoft2050.coincryptoinfo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.avvsoft2050.coincryptoinfo.R
import com.avvsoft2050.coincryptoinfo.pojo.Coins
import com.avvsoft2050.coincryptoinfo.pojo.FavoriteCoins
import com.avvsoft2050.coincryptoinfo.ui.coins.CoinsViewModel
import com.avvsoft2050.coincryptoinfo.utils.Converter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coins_markets.view.*
import kotlin.math.roundToInt

class CoinsAdapter(private val context: FragmentActivity) :
    RecyclerView.Adapter<CoinsAdapter.CoinsMarketsViewHolder>() {

    private lateinit var coinsViewModel: CoinsViewModel

    var coinsList: List<Coins> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var favoriteCoinsMarketsList: List<FavoriteCoins> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null
    var onCoinClickFavoriteListener: OnCoinClickFavoriteListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsMarketsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coins_markets, parent, false)
        return CoinsMarketsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinsMarketsViewHolder, position: Int) {
        val coin = coinsList[position]
        val red = context.resources.getColor(android.R.color.holo_red_dark)
        val green = context.resources.getColor(android.R.color.holo_green_dark)
        Picasso.get().load(coin.image).into(holder.ivCoinIcon)
        holder.tvMarketCapRank.text = coin.marketCapRank.toString()
        holder.tvSymbol.text = coin.symbol.uppercase()
        holder.tvName.text = coin.name
        coinsViewModel =
            ViewModelProvider(this.context).get(CoinsViewModel::class.java)
        coinsViewModel.getFavoriteCoinsMarketsBySymbol(coin.symbol)
            .observe(this.context, Observer {
                if (it != null) {
                    holder.ivFavorite.setImageResource(android.R.drawable.btn_star_big_on)
                } else {
                    holder.ivFavorite.setImageResource(android.R.drawable.btn_star_big_off)
                }
            })
        holder.tvFirstCurrencyLabel1.text = ""
        holder.tvFirstCurrentPrice.text = Converter.toUSCurrency(coin.currentPrice)
        holder.tvLastUpdatedLabel.text = context.getString(R.string.last_updated)
        holder.tvLastUpdated.text = coin.lastUpdated?.dropLast(5)
        val changeHour =
            coin.priceChangePercentage1hInCurrency?.let { (it * 100).roundToInt() / 100.0 }
        changeHour?.let {
            if (it < 0) {
                holder.tvHour.setTextColor(red)
                holder.tvHourLabel.setTextColor(red)
            } else {
                holder.tvHour.setTextColor(green)
                holder.tvHourLabel.setTextColor(green)
            }
        }
        holder.tvHour.text = changeHour.toString()
        holder.tvHourLabel.text = "% 1ч"
        val changeDay =
            coin.priceChangePercentage24hInCurrency?.let { (it * 100).roundToInt() / 100.0 }
        changeDay?.let {
            if (it < 0) {
                holder.tvDay.setTextColor(red)
                holder.tvDayLabel.setTextColor(red)
            } else {
                holder.tvDay.setTextColor(green)
                holder.tvDayLabel.setTextColor(green)
            }
        }
        holder.tvDay.text = changeDay.toString()
        holder.tvDayLabel.text = "% 1д"
        val change7Days =
            coin.priceChangePercentage7dInCurrency?.let { (it * 100).roundToInt() / 100.0 }
        change7Days?.let {
            if (it < 0) {
                holder.tv7Days.setTextColor(red)
                holder.tv7DaysLabel.setTextColor(red)
            } else {
                holder.tv7Days.setTextColor(green)
                holder.tv7DaysLabel.setTextColor(green)
            }
        }
        holder.tv7Days.text = change7Days.toString()
        holder.tv7DaysLabel.text = "% 7д"
        holder.itemView.setOnClickListener {
            onCoinClickListener?.onCoinClick(coin)
        }
//        holder.itemView.ivFavorite.setOnClickListener {
//            onCoinClickFavoriteListener?.onCoinClickFavoriteListener(FavoriteCoinsMarkets(coin))
//        }


    }

    override fun getItemCount() = coinsList.size

    inner class CoinsMarketsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCoinIcon: ImageView = itemView.ivCoinIcon
        val tvMarketCapRank: TextView = itemView.tvMarketCapRank
        val tvSymbol: TextView = itemView.tvSymbol
        val ivFavorite = itemView.ivFavorite
        val tvName = itemView.tvName
        val tvFirstCurrencyLabel1 = itemView.tvCurrencyLabelD
        val tvFirstCurrentPrice = itemView.tvFirstCurrentPriceD
        val tvLastUpdatedLabel = itemView.tvLastUpdatedLabel
        val tvLastUpdated = itemView.tvLastUpdated
        val tvHour = itemView.tvHour
        val tvHourLabel = itemView.tvHourLabel
        val tvDay = itemView.tvDay
        val tvDayLabel = itemView.tvDayLabel
        val tv7Days = itemView.tv7Days
        val tv7DaysLabel = itemView.tv7DaysLabel
    }

    interface OnCoinClickListener {
        fun onCoinClick(coins: Coins)
    }

    interface OnCoinClickFavoriteListener {
        fun onCoinClickFavoriteListener(coins: Coins)
    }

}
