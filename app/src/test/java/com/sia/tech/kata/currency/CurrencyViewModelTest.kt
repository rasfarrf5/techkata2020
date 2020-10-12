package com.sia.tech.kata.currency

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
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CurrencyViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var subject: CurrencyViewModel

    @Mock
    private lateinit var currencyProvider: CurrencyProvider

    @Mock
    private lateinit var compositeDisposable: CompositeDisposable

    @Mock
    private lateinit var currencyResultSuccess: CurrencyResult.Success

    @Mock
    private lateinit var currencyResultFailure: CurrencyResult.Failure

    @Test
    fun onViewCreated_givenCurrencyProviderReturnsSuccessResult_updatesDate() {
        `when`(currencyProvider.getCurrencyDetails())
            .thenReturn(Observable.just(currencyResultSuccess))
        `when`(currencyResultSuccess.date).thenReturn("date")

        subject.onViewCreated()

        assertThat(subject.currencyChangeDate.value).isEqualTo("date")
    }

    @Test
    fun onViewCreated_givenCurrencyProviderReturnsFailureResult_showErrorAndUpdatesDateWithEmpty() {
        `when`(currencyProvider.getCurrencyDetails())
            .thenReturn(Observable.just(currencyResultFailure))

        subject.onViewCreated()

        assertThat(subject.currencyChangeDate.value).isEqualTo("")
    }

    @Test
    fun onViewPaused() {

        subject.onViewPaused()

        verify(compositeDisposable).clear()
    }
}
