package com.avvsoft2050.coincryptoinfo.ui.favorite

import android.app.Application
import com.avvsoft2050.coincryptoinfo.database.AppDatabase
import com.avvsoft2050.coincryptoinfo.ui.coins.CoinsViewModel

class FavoriteCoinsViewModel(application: Application) : CoinsViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    var favoriteCoinsMarketsList = db.coinsMarketsDao().getFavoriteCoinsList()
}