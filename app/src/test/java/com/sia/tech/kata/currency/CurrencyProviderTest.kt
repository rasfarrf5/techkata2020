package com.sia.tech.kata.currency

import com.sia.tech.kata.currency.conversion.CurrencyConversionService
import com.sia.tech.kata.currency.conversion.model.CurrencyConversionResponse
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal

@RunWith(MockitoJUnitRunner::class)
class CurrencyProviderTest {

    @InjectMocks
    private lateinit var subject: CurrencyProvider

    @Mock
    private lateinit var currencyConversionService: CurrencyConversionService


    @Test
    fun getCurrencyDetails_givenCurrencyDetailsApiIsSuccess_returnsSuccessResult() {
        `when`(currencyConversionService.getLatestUpdate())
            .thenReturn(Observable.just(buildResponse()))

        val actualObserver = subject.getCurrencyDetails().test()

        actualObserver.assertValue { it is CurrencyResult.Success }
        actualObserver.assertValue {
            "12-10-2020" == (it as CurrencyResult.Success).date &&
                    BigDecimal.ONE == it.rates.USD &&
                    BigDecimal.valueOf(100.23) == it.rates.JPY &&
                    BigDecimal.valueOf(10.23) == it.rates.HKD &&
                    BigDecimal.valueOf(70.63) == it.rates.INR
        }
    }

    @Test
    fun getCurrencyDetails_givenCurrencyDetailsApiIsFailed_returnsFailure() {
        `when`(currencyConversionService.getLatestUpdate())
            .thenReturn(Observable.error(Throwable()))

        val actualObserver = subject.getCurrencyDetails().test()

        actualObserver.assertValue { it is CurrencyResult.Failure }
    }

    private fun buildResponse(): CurrencyConversionResponse {
        return CurrencyConversionResponse(
            CurrencyConversionResponse.Rates(
                BigDecimal.ONE,
                BigDecimal.valueOf(100.23),
                BigDecimal.valueOf(10.23),
                BigDecimal.valueOf(70.63)
            ),
            "12-10-2020"
        )
    }
}
