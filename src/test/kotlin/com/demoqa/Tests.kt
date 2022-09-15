package com.demoqa

import com.codeborne.selenide.Selenide.open
import com.demoqa.pages.Login
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [Config::class])
class Tests {
    @Autowired
    private lateinit var login: Login

    @Test
    fun `test`() {
        open("https://demoqa.com/login")
        login.waitWhileReady()
    }
}