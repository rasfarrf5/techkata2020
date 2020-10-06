package com.sia.tech.kata.main

import androidx.lifecycle.*
import javax.inject.Inject

class MainViewModel @Inject constructor() : ViewModel(), LifecycleObserver {

    var navigateTo = MutableLiveData(false)

    fun onLoginClicked() {
        navigateTo.value = true
    }
}