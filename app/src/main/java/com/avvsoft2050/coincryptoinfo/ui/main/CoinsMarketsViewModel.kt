package com.avvsoft2050.coincryptoinfo.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.avvsoft2050.coincryptoinfo.api.ApiFactory
import com.avvsoft2050.coincryptoinfo.database.AppDatabase
import com.avvsoft2050.coincryptoinfo.pojo.CoinsMarkets
import com.avvsoft2050.coincryptoinfo.pojo.FavoriteCoinsMarkets
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CoinsMarketsViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()
    private val firstCurrency = "usd"
    private val secondCurrency = "rub"

    val coinsMarketsList = db.coinsMarketsDao().getCoinsMarketsList()
    val favoriteCoinsMarketsList = db.coinsMarketsDao().getFavoriteCoinsMarketsList()

    init {
        loadData()
    }

    fun getCoinInfo(symbol:String): LiveData<CoinsMarkets>{
        return db.coinsMarketsDao().getCoinInfo(symbol)
    }

    fun insertFavoriteCoinsMarkets(favoriteCoinsMarkets: FavoriteCoinsMarkets?){
        db.coinsMarketsDao().insertFavoriteCoinsMarkets(favoriteCoinsMarkets)
    }

    fun deleteFavoriteCoinsMarkets(favoriteCoinsMarkets: FavoriteCoinsMarkets){
        db.coinsMarketsDao().deleteFavoriteCoinsMarkets(favoriteCoinsMarkets)
    }

    fun getFavoriteCoinsMarketsBySymbol(symbol: String):FavoriteCoinsMarkets?{
        return db.coinsMarketsDao().getFavoriteCoinsMarketsBySymbol(symbol)
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
                Log.d("TEST_OF_LOADING_DATA", "Success: $it")
            }, {
                Log.d("TEST_OF_LOADING_DATA", "Failure: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text
}