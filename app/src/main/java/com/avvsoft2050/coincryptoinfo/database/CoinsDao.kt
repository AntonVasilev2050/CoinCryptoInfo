package com.avvsoft2050.coincryptoinfo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.avvsoft2050.coincryptoinfo.pojo.Coins
import com.avvsoft2050.coincryptoinfo.pojo.FavoriteCoins


@Dao
interface CoinsDao {
    @Query("SELECT * FROM coins_markets ORDER BY marketCapRank")
    fun getCoinsMarketsList():LiveData<List<Coins>>

    @Query("SELECT * FROM coins_markets WHERE symbol = :s LIMIT 1 ")
    fun getCoinInfo(s:String):LiveData<Coins>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinsMarketsList(list: List<Coins>)

    @Query("DELETE FROM coins_markets")
    fun deleteAllCoinsMarkets()

    @Query("SELECT * FROM favorite_coins_markets ORDER BY marketCapRank")
    fun getFavoriteCoinsMarketsList():LiveData<List<FavoriteCoins>>

    @Query("SELECT * FROM favorite_coins_markets WHERE symbol == :s LIMIT 1")
    fun getFavoriteCoinsMarketsBySymbol(s: String):LiveData<FavoriteCoins>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteCoinsMarkets(favoriteCoinsMarkets: FavoriteCoins)

    @Delete
    fun deleteFavoriteCoinsMarkets(favoriteCoinsMarkets: FavoriteCoins)

    @Query("DELETE FROM favorite_coins_markets")
    fun deleteAllFavoriteCoinsMarkets()
}
