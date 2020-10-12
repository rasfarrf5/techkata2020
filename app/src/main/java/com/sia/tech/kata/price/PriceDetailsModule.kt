package com.sia.tech.kata.price

import androidx.lifecycle.ViewModel
import com.sia.tech.kata.ViewModelModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PriceDetailsModule {

    @Binds
    @IntoMap
    @ViewModelModule.ViewModelKey(PriceDetailsViewModel::class)
    abstract fun bindPriceDetailsViewModel(mainViewModel: PriceDetailsViewModel): ViewModel
}
