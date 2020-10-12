package com.sia.tech.kata.price

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sia.tech.kata.CurrencyProvider
import com.sia.tech.kata.CurrencyResult
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.math.BigDecimal

@RunWith(MockitoJUnitRunner::class)
class PriceDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var subject: PriceDetailsViewModel

    @Mock
    private lateinit var currencyProvider: CurrencyProvider

    @Mock
    private lateinit var compositeDisposable: CompositeDisposable

    @Mock
    private lateinit var currencyResultSuccess: CurrencyResult.Success

    @Mock
    private lateinit var rates: CurrencyResult.Rates

    @Mock
    private lateinit var currencyResultFailure: CurrencyResult.Failure

    @Test
    fun showPriceClicked_givenCurrencyProviderReturnsSuccessResult_updatesDate() {
        `when`(currencyProvider.getCurrencyDetails())
            .thenReturn(Observable.just(currencyResultSuccess))
        `when`(currencyResultSuccess.rates).thenReturn(rates)
        `when`(rates.USD).thenReturn(BigDecimal.TEN)

        subject.showPriceClicked()

        assertThat(subject.price.value).isEqualTo("10")
    }

    @Test
    fun showPriceClicked_givenCurrencyProviderReturnsFailureResult_showErrorAndUpdatesDateWithEmpty() {
        `when`(currencyProvider.getCurrencyDetails())
            .thenReturn(Observable.just(currencyResultFailure))

        subject.showPriceClicked()

        assertThat(subject.price.value).isEqualTo("")
    }

    @Test
    fun onViewPaused() {

        subject.onViewPaused()

        Mockito.verify(compositeDisposable).clear()
    }
}
