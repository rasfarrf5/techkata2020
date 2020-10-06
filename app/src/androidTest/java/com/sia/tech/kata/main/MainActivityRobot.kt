package com.sia.tech.kata.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.sia.tech.kata.R

class MainActivityRobot {

    fun seesLoginButton(): MainActivityRobot {
        onView(withId(R.id.loginButton))
            .check(matches(isDisplayed()))
        return this
    }

    fun clicksButton(): MainActivityRobot {
        onView(withId(R.id.loginButton))
            .perform(click())
        return this
    }
}