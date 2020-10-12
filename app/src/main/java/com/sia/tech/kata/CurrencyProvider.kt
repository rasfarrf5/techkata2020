package com.sia.tech.kata

import com.sia.tech.kata.currency.conversion.CurrencyConversionService
import com.sia.tech.kata.currency.conversion.model.CurrencyConversionResponse
import io.reactivex.Observable
import javax.inject.Inject

class CurrencyProvider @Inject constructor(
    private val currencyConversionService: CurrencyConversionService
) {
    fun getCurrencyDetails(): Observable<CurrencyResult> {

        return currencyConversionService.getLatestUpdate()
            .map {
                it.convert() as CurrencyResult
            }
            .onErrorReturn { CurrencyResult.Failure }

    }


    private fun CurrencyConversionResponse.convert(): CurrencyResult.Success {
        return CurrencyResult.Success(rates.convert(), date)
    }

    private fun CurrencyConversionResponse.Rates.convert(): CurrencyResult.Rates {
        return CurrencyResult.Rates(USD, JPY, HKD, INR)
    }
}
