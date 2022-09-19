package com.demoqa.pages

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class Login {
    //General
    private val header: SelenideElement = `$`(".main-header")
    private val error: SelenideElement = `$`("#output #name")

    //Fields
    private val passwordField: SelenideElement = `$`("#password")
    private val usernameField: SelenideElement = `$`("#userName")

    //Buttons
    private val loginBtn: SelenideElement = `$`("#login")
    private val newUserBtn: SelenideElement = `$`("#newUser")

    //String selectors
    private val invalidsCss: String = ".is-invalid"

    fun waitWhileReady() {
        header.shouldBe(visible).shouldHave(text("Login"))
        usernameField.shouldBe(visible, enabled)
        passwordField.shouldBe(visible, enabled)
    }

    fun newUserButtonClick() {
        waitWhileReady()
        newUserBtn
            .shouldBe(visible, enabled)
            .click()
    }
}