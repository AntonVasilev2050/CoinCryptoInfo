package com.avvsoft2050.coincryptoinfo.ui.favorite

import android.app.Application
import com.avvsoft2050.coincryptoinfo.database.AppDatabase
import com.avvsoft2050.coincryptoinfo.ui.coins.CoinsMarketsViewModel

class FavoriteCoinsMarketsViewModel(application: Application) : CoinsMarketsViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    val favoriteCoinsMarketsList = db.coinsMarketsDao().getFavoriteCoinsMarketsList()

}