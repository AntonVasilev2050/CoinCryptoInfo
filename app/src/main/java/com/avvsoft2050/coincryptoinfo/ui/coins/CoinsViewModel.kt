package com.avvsoft2050.coincryptoinfo.ui.coins

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.avvsoft2050.coincryptoinfo.api.ApiFactory
import com.avvsoft2050.coincryptoinfo.database.AppDatabase
import com.avvsoft2050.coincryptoinfo.pojo.Coins
import com.avvsoft2050.coincryptoinfo.pojo.FavoriteCoins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

open class CoinsViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
    private val firstCurrency = "usd"
    private val secondCurrency = "rub"

    val coinsList = db.coinsDao().getCoinsList()

    init {
        loadData()
    }

    fun getCoinInfo(symbol:String): LiveData<Coins>{
        return db.coinsDao().getCoinInfo(symbol)
    }

    fun getFavoriteCoinsMarketsBySymbol(s: String):LiveData<FavoriteCoins>{
        return db.coinsDao().getFavoriteCoinsBySymbol(s)
    }


    fun insertFavoriteCoins(favoriteCoins: FavoriteCoins){
        val disposable = ApiFactory.apiService.getCoins(perPage = 100)
            .delaySubscription(30, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.coinsDao().insertFavoriteCoins(favoriteCoins)
            }, {
                Log.d("TEST_OF_LOADING_DATA", "Failure insert to Favorite: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    fun deleteFavoriteCoins(favoriteCoins: FavoriteCoins){
        val disposable = ApiFactory.apiService.getCoins(perPage = 100)
            .delaySubscription(30, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.coinsDao().deleteFavoriteCoins(favoriteCoins)
            }, {
                Log.d("TEST_OF_LOADING_DATA", "Failure delete from Favorite: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    private fun loadData(){
        val disposable = ApiFactory.apiService.getCoins(vsCurrency = firstCurrency, perPage = 100)
            .delaySubscription(30, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                db.coinsDao().insertCoinsList(it)
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