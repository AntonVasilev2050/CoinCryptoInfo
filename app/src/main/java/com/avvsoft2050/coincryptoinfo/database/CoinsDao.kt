package com.avvsoft2050.coincryptoinfo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.avvsoft2050.coincryptoinfo.pojo.Coins
import com.avvsoft2050.coincryptoinfo.pojo.FavoriteCoins


@Dao
interface CoinsDao {
    @Query("SELECT * FROM coins ORDER BY marketCapRank")
    fun getCoinsList():LiveData<List<Coins>>

    @Query("SELECT * FROM coins WHERE symbol = :s LIMIT 1 ")
    fun getCoinInfo(s:String):LiveData<Coins>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinsList(list: List<Coins>)

    @Query("DELETE FROM coins")
    fun deleteAllCoins()

    @Query("SELECT * FROM favorite_coins ORDER BY marketCapRank")
    fun getFavoriteCoinsList():LiveData<MutableList<FavoriteCoins>>

    @Query("SELECT * FROM favorite_coins WHERE symbol == :s LIMIT 1")
    fun getFavoriteCoinsBySymbol(s: String):LiveData<FavoriteCoins>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteCoins(favoriteCoins: FavoriteCoins)

    @Delete
    fun deleteFavoriteCoins(favoriteCoins: FavoriteCoins)

    @Query("DELETE FROM favorite_coins")
    fun deleteAllFavoriteCoins()
}
