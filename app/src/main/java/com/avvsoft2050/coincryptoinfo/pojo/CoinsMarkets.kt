package com.avvsoft2050.coincryptoinfo.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coins_markets")
data class CoinsMarkets (
    @SerializedName("id")
    @Expose
    val id: String?,

    @PrimaryKey
    @SerializedName("symbol")
    @Expose
    val symbol: String,

    @SerializedName("name")
    @Expose
    val name: String?,

    @SerializedName("image")
    @Expose
    val image: String?,

    @SerializedName("current_price")
    @Expose
    val currentPrice: Double?,

    @SerializedName("market_cap")
    @Expose
    val marketCap: Long?,

    @SerializedName("market_cap_rank")
    @Expose
    val marketCapRank: Int?,

    @SerializedName("fully_diluted_valuation")
    @Expose
    val fullyDilutedValuation: Long?,

    @SerializedName("total_volume")
    @Expose
    val totalVolume: Long?,

    @SerializedName("high_24h")
    @Expose
    val high24h: Double?,

    @SerializedName("low_24h")
    @Expose
    val low24h: Double?,

    @SerializedName("price_change_24h")
    @Expose
    val priceChange24h: Double?,

    @SerializedName("price_change_percentage_24h")
    @Expose
    val priceChangePercentage24h: Double?,

    @SerializedName("market_cap_change_24h")
    @Expose
    val marketCapChange24h: Double?,

    @SerializedName("market_cap_change_percentage_24h")
    @Expose
    val marketCapChangePercentage24h: Double?,

    @SerializedName("circulating_supply")
    @Expose
    val circulatingSupply: Double?,

    @SerializedName("total_supply")
    @Expose
    val totalSupply: Double?,

    @SerializedName("max_supply")
    @Expose
    val maxSupply: Double?,

    @SerializedName("ath")
    @Expose
    val ath: Double?,

    @SerializedName("ath_change_percentage")
    @Expose
    val athChangePercentage: Double?,

    @SerializedName("ath_date")
    @Expose
    val athDate: String?,

    @SerializedName("atl")
    @Expose
    val atl: Double?,

    @SerializedName("atl_change_percentage")
    @Expose
    val atlChangePercentage: Double?,

    @SerializedName("atl_date")
    @Expose
    val atlDate: String?,

//    @SerializedName("roi")
//    @Expose
//    val roi: Any?,

    @SerializedName("last_updated")
    @Expose
    val lastUpdated: String?,

    @SerializedName("price_change_percentage_14d_in_currency")
    @Expose
    val priceChangePercentage14dInCurrency: Double?,

    @SerializedName("price_change_percentage_1h_in_currency")
    @Expose
    val priceChangePercentage1hInCurrency: Double?,

    @SerializedName("price_change_percentage_1y_in_currency")
    @Expose
    val priceChangePercentage1yInCurrency: Double?,

    @SerializedName("price_change_percentage_200d_in_currency")
    @Expose
    val priceChangePercentage200dInCurrency: Double?,

    @SerializedName("price_change_percentage_24h_in_currency")
    @Expose
    val priceChangePercentage24hInCurrency: Double?,

    @SerializedName("price_change_percentage_30d_in_currency")
    @Expose
    val priceChangePercentage30dInCurrency: Double?,

    @SerializedName("price_change_percentage_7d_in_currency")
    @Expose
    val priceChangePercentage7dInCurrency: Double?,

)