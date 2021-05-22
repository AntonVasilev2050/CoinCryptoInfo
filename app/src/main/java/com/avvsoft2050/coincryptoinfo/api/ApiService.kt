package com.avvsoft2050.coincryptoinfo.api

import com.avvsoft2050.coincryptoinfo.pojo.CoinsMarkets
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("coins/markets")
    fun getCoinsMarkets(
        @Query(QUERY_PARAM_VS_CURRENCY) vsCurrency: String = "usd",
        @Query(QUERY_PARAM_ORDER) order: String = "market_cap_desc",
        @Query(QUERY_PARAM_PER_PAGE) perPage: Int = 20,
        @Query(QUERY_PARAM_PAGE) page: Int = 1,
        @Query(QUERY_PARAM_SPARKLINE) sparkline: Boolean = false,
        @Query(QUERY_PARAM_PRICE_CHANGE_PERCENTAGE) priceChangePercentage: String = "1h,24h,7d,14d,30d,200d,1y"
    ): Single<List<CoinsMarkets>>

    companion object {
        private const val QUERY_PARAM_VS_CURRENCY = "vs_currency"
        private const val QUERY_PARAM_ORDER = "order"
        private const val QUERY_PARAM_PER_PAGE = "per_page"
        private const val QUERY_PARAM_PAGE = "page"
        private const val QUERY_PARAM_SPARKLINE = "sparkline"
        private const val QUERY_PARAM_PRICE_CHANGE_PERCENTAGE = "price_change_percentage"
    }
}