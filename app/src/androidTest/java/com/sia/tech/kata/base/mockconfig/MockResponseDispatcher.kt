package com.sia.tech.kata.base.mockconfig

import android.os.Handler
import androidx.test.platform.app.InstrumentationRegistry
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockResponseDispatcher internal constructor() : Dispatcher() {

    private val weatherHandler = CurrencyRequestHandler()

    override fun dispatch(request: RecordedRequest): MockResponse {

        return if (weatherHandler.canHandleRequest(request)) {
            weatherHandler.getResponse(request)
        } else {
            throwUnsupportedException("Could not handle", request.path)
        }
    }

    private fun throwUnsupportedException(message: String, path: String): MockResponse {
        val mainThreadHandler = Handler(
            InstrumentationRegistry.getInstrumentation().targetContext.mainLooper
        )
        mainThreadHandler.post { throw UnsupportedOperationException("$message $path") }

        throw UnsupportedOperationException()
    }
}
