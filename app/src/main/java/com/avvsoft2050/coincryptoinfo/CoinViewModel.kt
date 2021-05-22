package com.avvsoft2050.coincryptoinfo

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.avvsoft2050.coincryptoinfo.api.ApiFactory
import com.avvsoft2050.coincryptoinfo.database.AppDatabase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CoinViewModel(application: Application):AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val compositeDisposable = CompositeDisposable()

    val coinsMarketsList = db.coinsMarketsDao().getCoinsMarketsList()

    fun loadData(){
        val disposable = ApiFactory.apiService.getCoinsMarkets()
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
}