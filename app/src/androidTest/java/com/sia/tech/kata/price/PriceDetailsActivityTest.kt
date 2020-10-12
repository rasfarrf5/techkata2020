package com.sia.tech.kata.price

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.sia.tech.kata.base.TechKataTestRule
import com.sia.tech.kata.base.mockconfig.MockWebServerRobot
import com.sia.tech.kata.base.mockconfig.MockWebServerRule
import com.sia.tech.kata.base.mockconfig.UserAction
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class PriceDetailsActivityTest {

    @get:Rule
    val activityRule = TechKataTestRule(PriceDetailsActivity::class.java)

    @get:Rule
    val mockWebServerRule = MockWebServerRule()

    private val mockWebServerRobot = MockWebServerRobot(mockWebServerRule)
    private val priceDetailsActivityRobot = PriceDetailsActivityRobot()

    @Test
    fun onLaunchPriceDetailsScreen_seesUsdPrice() {
        activityRule.launchActivity()

        mockWebServerRobot
            .useDefaultDispatcher()
            .performNoSyncAction(object : UserAction {
                override fun perform() {
                    priceDetailsActivityRobot
                        .clicksShowPriceButton()
                }
            })
        priceDetailsActivityRobot.seesPrice("1.1795")
    }

}
