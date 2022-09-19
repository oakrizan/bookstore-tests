package com.demoqa.pages

import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class Registration {
    //General
    private val header: SelenideElement = `$`("#userForm h4")
    //Fields
    private val firstname: SelenideElement = `$`("#firstname")
    private val lastname: SelenideElement = `$`("#lastname")
    private val username: SelenideElement = `$`("#username")
    private val password: SelenideElement = `$`("#password")
}