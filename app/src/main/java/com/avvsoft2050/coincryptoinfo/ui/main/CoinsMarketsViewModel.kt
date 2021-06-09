package com.avvsoft2050.coincryptoinfo.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.avvsoft2050.coincryptoinfo.api.ApiFactory
import com.avvsoft2050.coincryptoinfo.database.AppDatabase
import com.avvsoft2050.coincryptoinfo.pojo.CoinsMarkets
import com.avvsoft2050.coincryptoinfo.pojo.FavoriteCoinsMarkets
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

open class CoinsMarketsViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
    private val firstCurrency = "usd"
    private val secondCurrency = "rub"

    val coinsMarketsList = db.coinsMarketsDao().getCoinsMarketsList()

    init {
        loadData()
    }

    fun getCoinInfo(symbol:String): LiveData<CoinsMarkets>{
        return db.coinsMarketsDao().getCoinInfo(symbol)
    }

    fun getFavoriteCoinsMarketsBySymbol(s: String):LiveData<FavoriteCoinsMarkets>{
        return db.coinsMarketsDao().getFavoriteCoinsMarketsBySymbol(s)
    }

    fun insertFavoriteCoinsMarkets(favoriteCoinsMarkets: FavoriteCoinsMarkets){
        val disposable = ApiFactory.apiService.getCoinsMarkets(perPage = 100)
            .delaySubscription(100, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                db.coinsMarketsDao().insertFavoriteCoinsMarkets(favoriteCoinsMarkets)
            }, {
                Log.d("TEST_OF_LOADING_DATA", "Failure insert to Favorite: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    fun deleteFavoriteCoinsMarkets(favoriteCoinsMarkets: FavoriteCoinsMarkets){
        val disposable = ApiFactory.apiService.getCoinsMarkets(perPage = 100)
            .delaySubscription(100, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                db.coinsMarketsDao().deleteFavoriteCoinsMarkets(favoriteCoinsMarkets)
            }, {
                Log.d("TEST_OF_LOADING_DATA", "Failure delete from Favorite: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    fun deleteAllFavoriteCoinsMarkets(){
        val disposable = ApiFactory.apiService.getCoinsMarkets(perPage = 100)
            .delaySubscription(100, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                db.coinsMarketsDao().deleteAllFavoriteCoinsMarkets()
            }, {
                Log.d("TEST_OF_LOADING_DATA", "Failure delete all from Favorite: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    private fun loadData(){
        val disposable = ApiFactory.apiService.getCoinsMarkets(vsCurrency = firstCurrency, perPage = 100)
            .delaySubscription(10, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                db.coinsMarketsDao().insertCoinsMarketsList(it)
            }, {
                Log.d("TEST_OF_LOADING_DATA", "Failure: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}