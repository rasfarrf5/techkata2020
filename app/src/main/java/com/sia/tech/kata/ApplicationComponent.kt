package com.sia.tech.kata

import com.sia.tech.kata.currency.CurrencyActivity
import com.sia.tech.kata.currency.CurrencyModule
import com.sia.tech.kata.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,

        AppModule::class,
        ViewModelModule::class,

        CurrencyModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(techKataApplication: TechKataApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(techKataApplication: TechKataApplication)

    fun inject(mainActivity: MainActivity)

    fun inject(currencyActivity: CurrencyActivity)
}