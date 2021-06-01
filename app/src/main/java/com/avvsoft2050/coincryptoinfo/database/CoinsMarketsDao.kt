package com.avvsoft2050.coincryptoinfo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.avvsoft2050.coincryptoinfo.pojo.CoinsMarkets
import com.avvsoft2050.coincryptoinfo.pojo.FavoriteCoinsMarkets


@Dao
interface CoinsMarketsDao {
    @Query("SELECT * FROM coins_markets ORDER BY marketCapRank")
    fun getCoinsMarketsList():LiveData<List<CoinsMarkets>>

    @Query("SELECT * FROM coins_markets WHERE symbol = :s LIMIT 1 ")
    fun getCoinInfo(s:String):LiveData<CoinsMarkets>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinsMarketsList(marketsList: List<CoinsMarkets>)

    @Query("DELETE FROM coins_markets")
    fun deleteAllCoinsMarkets()

    @Query("SELECT * FROM favorite_coins_markets ORDER BY marketCapRank")
    fun getFavoriteCoinsMarketsList():LiveData<List<FavoriteCoinsMarkets>>

    @Query("SELECT * FROM favorite_coins_markets WHERE symbol == :s")
    fun getFavoriteCoinsMarketsBySymbol(s: String):FavoriteCoinsMarkets?

    @Insert
    fun insertFavoriteCoinsMarkets(favoriteCoinsMarkets: FavoriteCoinsMarkets?)

    @Delete
    fun deleteFavoriteCoinsMarkets(favoriteCoinsMarkets: FavoriteCoinsMarkets)

    @Query("DELETE FROM favorite_coins_markets")
    fun deleteAllFavoriteCoinsMarkets()
}
