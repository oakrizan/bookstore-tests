package com.demoqa

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide.open
import com.demoqa.objects.user.UserDTO
import com.demoqa.pages.Login
import com.demoqa.pages.Registration
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [Config::class])
class Tests() {
    @Autowired
    private lateinit var login: Login

    @Autowired
    private lateinit var registration: Registration

    @Autowired
    private lateinit var userDTO: UserDTO

    @Test
    fun `test`() {
        Configuration.browserSize = "2100x1080"
        open("https://demoqa.com/login")
        login.waitWhileReady()
        login.newUserButtonClick()
        val uuuu = userDTO.newUser()
        println("aaaaa")
    }
}