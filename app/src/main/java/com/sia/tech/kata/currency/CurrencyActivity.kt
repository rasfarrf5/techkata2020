package com.sia.tech.kata.currency

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.sia.tech.kata.R
import com.sia.tech.kata.TechKataApplication
import com.sia.tech.kata.databinding.ActivityCurrencyBinding
import kotlinx.android.synthetic.main.activity_currency.*
import javax.inject.Inject

class CurrencyActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var currencyViewModel: CurrencyViewModel
    private lateinit var viewDataBinding: ViewDataBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as TechKataApplication).appComponent.inject(this)
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_currency)

        currencyViewModel = ViewModelProvider(this, viewModelFactory)
            .get(CurrencyViewModel::class.java)

        lifecycle.addObserver(currencyViewModel)

        (viewDataBinding as ActivityCurrencyBinding).lifecycleOwner = this
        (viewDataBinding as ActivityCurrencyBinding).currencyViewModel = currencyViewModel

        currencyViewModel.currencyLiveData.observe(this, {
            currencyText.text = it
        })
    }
}