package com.sia.tech.kata.price

import androidx.lifecycle.*
import com.sia.tech.kata.CurrencyProvider
import com.sia.tech.kata.CurrencyResult
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PriceDetailsViewModel @Inject constructor(
    private val currencyProvider: CurrencyProvider,
    private val compositeDisposable: CompositeDisposable
) : ViewModel(), LifecycleObserver {

    val price = MutableLiveData("")


    fun showPriceClicked() {

        val disposable = currencyProvider.getCurrencyDetails()
            .subscribe {
                if (it is CurrencyResult.Success) {
                    price.postValue(it.rates.USD.toPlainString())
                } else {
                    price.postValue("")
                }
            }
        compositeDisposable.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onViewPaused() {
        compositeDisposable.clear()
    }

}
