package com.avvsoft2050.coincryptoinfo.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coins_markets")
open class CoinsMarkets (
    @SerializedName("id")
    @Expose
    var id: String?,

    @PrimaryKey
    @SerializedName("symbol")
    @Expose
    var symbol: String,

    @SerializedName("name")
    @Expose
    var name: String?,

    @SerializedName("image")
    @Expose
    var image: String?,

    @SerializedName("current_price")
    @Expose
    var currentPrice: Double?,

    @SerializedName("market_cap")
    @Expose
    var marketCap: Long?,

    @SerializedName("market_cap_rank")
    @Expose
    var marketCapRank: Int?,

    @SerializedName("fully_diluted_valuation")
    @Expose
    var fullyDilutedValuation: Long?,

    @SerializedName("total_volume")
    @Expose
    var totalVolume: Long?,

    @SerializedName("high_24h")
    @Expose
    var high24h: Double?,

    @SerializedName("low_24h")
    @Expose
    var low24h: Double?,

    @SerializedName("price_change_24h")
    @Expose
    var priceChange24h: Double?,

    @SerializedName("price_change_percentage_24h")
    @Expose
    var priceChangePercentage24h: Double?,

    @SerializedName("market_cap_change_24h")
    @Expose
    var marketCapChange24h: Double?,

    @SerializedName("market_cap_change_percentage_24h")
    @Expose
    var marketCapChangePercentage24h: Double?,

    @SerializedName("circulating_supply")
    @Expose
    var circulatingSupply: Double?,

    @SerializedName("total_supply")
    @Expose
    var totalSupply: Double?,

    @SerializedName("max_supply")
    @Expose
    var maxSupply: Double?,

    @SerializedName("ath")
    @Expose
    var ath: Double?,

    @SerializedName("ath_change_percentage")
    @Expose
    var athChangePercentage: Double?,

    @SerializedName("ath_date")
    @Expose
    var athDate: String?,

    @SerializedName("atl")
    @Expose
    var atl: Double?,

    @SerializedName("atl_change_percentage")
    @Expose
    var atlChangePercentage: Double?,

    @SerializedName("atl_date")
    @Expose
    var atlDate: String?,

//    @SerializedName("roi")
//    @Expose
//    val roi: Any?,

    @SerializedName("last_updated")
    @Expose
    var lastUpdated: String?,

    @SerializedName("price_change_percentage_14d_in_currency")
    @Expose
    var priceChangePercentage14dInCurrency: Double?,

    @SerializedName("price_change_percentage_1h_in_currency")
    @Expose
    var priceChangePercentage1hInCurrency: Double?,

    @SerializedName("price_change_percentage_1y_in_currency")
    @Expose
    var priceChangePercentage1yInCurrency: Double?,

    @SerializedName("price_change_percentage_200d_in_currency")
    @Expose
    var priceChangePercentage200dInCurrency: Double?,

    @SerializedName("price_change_percentage_24h_in_currency")
    @Expose
    var priceChangePercentage24hInCurrency: Double?,

    @SerializedName("price_change_percentage_30d_in_currency")
    @Expose
    var priceChangePercentage30dInCurrency: Double?,

    @SerializedName("price_change_percentage_7d_in_currency")
    @Expose
    var priceChangePercentage7dInCurrency: Double?

)