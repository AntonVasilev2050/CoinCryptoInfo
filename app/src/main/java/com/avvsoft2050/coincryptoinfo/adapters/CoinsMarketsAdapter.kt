package com.avvsoft2050.coincryptoinfo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avvsoft2050.coincryptoinfo.R
import com.avvsoft2050.coincryptoinfo.pojo.CoinsMarkets
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_coins_markets.view.*

class CoinsMarketsAdapter: RecyclerView.Adapter<CoinsMarketsAdapter.CoinsMarketsViewHolder>() {

    var coinsMarketsList: List<CoinsMarkets> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsMarketsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coins_markets, parent, false)
        return CoinsMarketsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinsMarketsViewHolder, position: Int) {
        val coin = coinsMarketsList[position]
        Picasso.get().load(coin.image).into(holder.ivCoinIcon)
        holder.tvMarketCapRank.text = "${coin.marketCapRank.toString()}."
        holder.tvSymbol.text = coin.symbol.uppercase()
        holder.tvName.text = coin.name
        holder.tvFirstCurrencyLabel1.text = "$"
        holder.tvFirstCurrentPrice.text = coin.currentPrice.toString()
        holder.tvSecondCurrencyLabel2.text = "P"
//        holder.tvSecondCurrentPrice.text = " "
        val changeHour = coin.priceChangePercentage1hInCurrency?.let { Math.round(it * 100) / 100.0 }
        holder.tvHour.text = changeHour.toString()
        holder.tvHourLabel.text = "% 1ч"

        holder.tvDay.text = coin.priceChangePercentage24hInCurrency.toString()
        holder.tvDayLabel.text = "% 1д"
        holder.tv7Days.text = coin.priceChangePercentage7dInCurrency.toString()
        holder.tv7DaysLabel.text = "% 7д"

    }

    override fun getItemCount() = coinsMarketsList.size

    inner class CoinsMarketsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val ivCoinIcon = itemView.ivCoinIcon
        val tvMarketCapRank = itemView.tvMarketCapRank
        val tvSymbol = itemView.tvSymbol
        val ivFavorite = itemView.ivFavorite
        val tvName = itemView.tvName
        val tvFirstCurrencyLabel1 = itemView.tvFirstCurrencyLabel1
        val tvFirstCurrentPrice = itemView.tvFirstCurrentPrice
        val tvSecondCurrencyLabel2 = itemView.tvSecondCurrencyLabel2
        val tvSecondCurrentPrice = itemView.tvSecondCurrentPrice
        val tvHour = itemView.tvHour
        val tvHourLabel = itemView.tvHourLabel
        val tvDay = itemView.tvDay
        val tvDayLabel = itemView.tvDayLabel
        val tv7Days = itemView.tv7Days
        val tv7DaysLabel = itemView.tv7DaysLabel
    }
}