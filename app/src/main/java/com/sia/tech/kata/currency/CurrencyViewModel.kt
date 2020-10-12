package com.sia.tech.kata.currency

import androidx.lifecycle.*
import com.sia.tech.kata.CurrencyProvider
import com.sia.tech.kata.CurrencyResult
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(
    private val currencyProvider: CurrencyProvider,
    private val compositeDisposable: CompositeDisposable
) : ViewModel(), LifecycleObserver {

    var currencyChangeDate = MutableLiveData("")

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onViewCreated() {
        currencyChangeDate.postValue("Date will be display here!")

        val disposable = currencyProvider.getCurrencyDetails()
            .subscribe {
                if (it is CurrencyResult.Success) {
                    currencyChangeDate.postValue(it.date)
                } else {
                    currencyChangeDate.postValue("")
                }
            }

        compositeDisposable.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onViewPaused() {
        compositeDisposable.clear()
    }
}
