package com.avvsoft2050.coincryptoinfo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.avvsoft2050.coincryptoinfo.pojo.CoinsMarkets

@Dao
interface CoinsMarketsDao {
    @Query("SELECT * FROM coins_markets ORDER BY marketCapRank")
    fun getCoinsMarketsList():LiveData<List<CoinsMarkets>>

    @Query("SELECT * FROM coins_markets WHERE symbol = :s LIMIT 1 ")
    fun getCoinInfo(s:String):LiveData<CoinsMarkets>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoinsMarketsList(marketsList: List<CoinsMarkets>)
}