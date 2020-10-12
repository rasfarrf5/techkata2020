package com.sia.tech.kata.network

import android.content.Context
import com.sia.tech.kata.network.support.NetworkConfiguration
import dagger.BindsInstance
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent {

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance configuration: NetworkConfiguration): NetworkComponent
    }

    fun retrofit(): Retrofit

    fun okHttpClient(): OkHttpClient
}
