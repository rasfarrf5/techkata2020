package com.sia.tech.kata.price

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.sia.tech.kata.R
import com.sia.tech.kata.TechKataApplication
import com.sia.tech.kata.databinding.ActivityPriceDetailsBinding
import javax.inject.Inject

class PriceDetailsActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var priceDetailsViewModel: PriceDetailsViewModel
    private lateinit var viewDataBinding: ViewDataBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as TechKataApplication).appComponent.inject(this)

        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_price_details)

        priceDetailsViewModel = ViewModelProvider(this, viewModelFactory)
            .get(PriceDetailsViewModel::class.java)

        lifecycle.addObserver(priceDetailsViewModel)

        (viewDataBinding as ActivityPriceDetailsBinding).lifecycleOwner = this
        (viewDataBinding as ActivityPriceDetailsBinding).priceDetailsViewModel = priceDetailsViewModel


    }

}
