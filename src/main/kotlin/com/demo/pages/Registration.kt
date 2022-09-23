package com.demo.pages

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class Registration {
    companion object{
        const val HEADER_TEXT = "Multi Step Form"
        const val STEP_1_TEXT = "Account Setup"
        const val STEP_2_TEXT = "Personal Details"
        const val STEP_3_TEXT = "Additional Details"
    }
    //General
    private val header: SelenideElement = `$`("#main h1")
    private val stepBar: SelenideElement = `$`(".user-registration-multi-part--steps-list")

    //Fields
    private val username: SelenideElement = `$`("#user_login")
    private val email: SelenideElement = `$`("#user_email")
    private val password: SelenideElement = `$`("#user_pass")
    private val confirmPassword: SelenideElement = `$`("#user_confirm_password")

    private val firstname: SelenideElement = `$`("#first_name")
    private val lastname: SelenideElement = `$`("#last_name")
    private val dateOfBirth: SelenideElement = `$`("#load_flatpickr[data-label=\"Date of birth\"]")
    private val conditionText: SelenideElement = `$`("[id*=\"textarea\"].input-text")
    private val appointmentDate: SelenideElement = `$`("#load_flatpickr[data-label=\"Date\"]")
    private val appointmentTime: SelenideElement = `$`("[data-label=\"Time Picker\"]")

    //Buttons
    private val hasAttended: SelenideElement = `$`("[type=\"radio\"][id*=\"yes\"]")
    private val hasNotAttended: SelenideElement = `$`("[type=\"radio\"][id*=\"no\"]")
    private val next: SelenideElement = `$`("[data-action=\"next\"]")
    private val submit: SelenideElement = `$`("[type=\"submit\"]")

    fun `wait while ready`() {
        header.shouldBe(visible)
            .shouldHave(text(HEADER_TEXT))
        stepBar.shouldBe(visible)
    }

    fun `do first step`(firstName: String) {

    }
}