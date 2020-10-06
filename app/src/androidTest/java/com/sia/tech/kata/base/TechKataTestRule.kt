package com.sia.tech.kata.base

import android.app.Activity
import android.content.Intent
import androidx.test.espresso.intent.rule.IntentsTestRule

class TechKataTestRule<T : Activity>(clazz: Class<T>) :
    IntentsTestRule<T>(clazz, false, false) {

    fun launchActivity(): T {
        return super.launchActivity(null)
    }

    override fun launchActivity(intent: Intent?): T {
        return super.launchActivity(intent)
    }
}