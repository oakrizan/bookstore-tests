package com.demo.pages

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selectors.byText
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.demo.objects.user.ClientData
import com.demo.pages.common.DatePicker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class Registration {
    companion object {
        const val HEADER_TEXT = "Multi Step Form"
        const val STEP_1_TEXT = "Account Setup"
        const val STEP_2_TEXT = "Personal Details"
        const val STEP_3_TEXT = "Additional Details"
        const val SUCCESS_REGISTRATION_TEXT = "User successfully registered."
    }

    //General
    private val header: SelenideElement = `$`("#main h1")
    private val stepBar: SelenideElement = `$`(".user-registration-multi-part--steps-list")
    private val activeStep: String = ".active"
    private val successMessage: SelenideElement = `$`("#ur-submit-message-node")

    //Fields
    private val username: SelenideElement = `$`("#user_login")
    private val email: SelenideElement = `$`("#user_email")
    private val password: SelenideElement = `$`("#user_pass")
    private val confirmPassword: SelenideElement = `$`("#user_confirm_password")
    private val firstname: SelenideElement = `$`("#first_name")
    private val lastname: SelenideElement = `$`("#last_name")
    private val phoneNumber: SelenideElement = `$`("[data-label=\"Phone\"]")
    private val dateOfBirth: SelenideElement = `$`("[id*=date_box] .input-wrapper")
    private val country: SelenideElement = `$`("[data-label=\"Country\"]")
    private val displayName: SelenideElement = `$`("#display_name")
    private val aboutText: SelenideElement = `$`("[data-label\$=\"about yourself\"]")

    //Buttons & Checkboxes
    private val privacyPolicy: SelenideElement = `$`("[data-label=\"Privacy Policy\"] input")
    private val next: SelenideElement = `$`("[data-action=\"next\"]")
    private val submit: SelenideElement = `$`(".ur-button-container [type=\"submit\"]")

    @Autowired
    private lateinit var datePicker: DatePicker

    fun `wait while ready`() {
        header.shouldBe(visible)
            .shouldHave(text(HEADER_TEXT))
        stepBar.shouldBe(visible)
    }

    fun `do step 1`(client: ClientData) {
        `validate step readiness`(STEP_1_TEXT)
        username.`val`(client.username)
        email.`val`(client.email)
        password.`val`(client.password)
        confirmPassword.`val`(client.password)
        next.click()
    }

    fun `do step 2`(client: ClientData) {
        `validate step readiness`(STEP_2_TEXT)
        firstname.`val`(client.firstname)
        lastname.`val`(client.lastname)
        `gender element`(client.gender).click()
        phoneNumber.`val`(client.phoneNumber)
        `select date`(client.dateOfBirth)
        `select country`(client.country)
        next.click()
    }

    fun `do step 3`(client: ClientData) {
        `validate step readiness`(STEP_3_TEXT)
        displayName.`val`(client.username)
        aboutText.`val`(client.aboutText)
        privacyPolicy.click()
        submit.click()
    }

    fun `registration success message`(): SelenideElement {
        `wait while ready`()
        successMessage.shouldBe(visible)
        return successMessage
    }

    private fun `validate step readiness`(stepName: String) {
        `wait while ready`()
        stepBar.`$$`(activeStep)
            .last()
            .shouldHave(text(stepName))
    }

    private fun `gender element`(gender: String): SelenideElement {
        return `$`("[value= ${gender.replaceFirstChar(Char::titlecase)}]")
    }

    private fun `select date`(date: LocalDate) {
        dateOfBirth.click()
        datePicker.`select year`(date.year.toString())
        datePicker.`select month`(date.month.toString())
        datePicker.`select day`(date.dayOfMonth.toString())
    }

    private fun `select country`(countryName: String) {
        country.click()
        `$`(byText(countryName)).click()
    }
}