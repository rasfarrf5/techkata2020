package com.sia.tech.kata.currency

import java.math.BigDecimal

sealed class CurrencyResult {

    data class Success(
        val rates: Rates,
        val date: String,
    ) : CurrencyResult()

    object Failure : CurrencyResult()

    data class Rates(
        val USD: BigDecimal,
        val JPY: BigDecimal,
        val HKD: BigDecimal,
        val INR: BigDecimal
    )
}
