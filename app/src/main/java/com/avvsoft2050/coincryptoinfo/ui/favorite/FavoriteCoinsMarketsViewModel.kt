package com.avvsoft2050.coincryptoinfo.ui.favorite

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.avvsoft2050.coincryptoinfo.api.ApiFactory
import com.avvsoft2050.coincryptoinfo.database.AppDatabase
import com.avvsoft2050.coincryptoinfo.pojo.FavoriteCoinsMarkets
import com.avvsoft2050.coincryptoinfo.ui.main.CoinsMarketsViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class FavoriteCoinsMarketsViewModel(application: Application) : CoinsMarketsViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    val favoriteCoinsMarketsList = db.coinsMarketsDao().getFavoriteCoinsMarketsList()

}