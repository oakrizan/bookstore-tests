package com.demo

import com.codeborne.selenide.Selenide.*
import org.springframework.stereotype.Component

@Component
class SessionManager {
    fun setupWith(url: String) {
        clearBrowserCookies()
        open(url)
    }

    fun tearDown() {
        closeWebDriver()
    }
}