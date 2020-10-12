package com.sia.tech.kata.price

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.sia.tech.kata.R

class PriceDetailsActivityRobot {

    fun clicksShowPriceButton() {
        onView(withId(R.id.showPriceButton))
            .perform(ViewActions.click())
    }

    fun seesPrice(titleString: String) {
        onView(withId(R.id.priceText))
            .check(matches(withText(titleString)))
    }
}
