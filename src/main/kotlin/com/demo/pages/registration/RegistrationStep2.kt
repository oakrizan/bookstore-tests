package com.demo.pages.registration

import com.codeborne.selenide.Condition.exist
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selectors.byText
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.demo.objects.client.ClientData
import com.demo.pages.common.DatePicker
import com.demo.pages.registration.StepName.STEP_2Name
import io.qameta.allure.Step
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class RegistrationStep2 {
    private val firstnameField: SelenideElement = `$`("#first_name")
    private val lastnameField: SelenideElement = `$`("#last_name")
    private val phoneNumberField: SelenideElement = `$`("[data-label=\"Phone\"]")
    private val dateOfBirthField: SelenideElement = `$`("[id*=date_box] .input-wrapper")
    private val countryField: SelenideElement = `$`("[data-label=\"Country\"]")
    private val genderOptionWrapper: SelenideElement = `$`("[id*=radio].form-row")

    @Autowired
    private lateinit var registration: RegistrationCommon

    @Autowired
    private lateinit var datePicker: DatePicker

    @Step("Provide data for 2nd step")
    fun `do step 2`(client: ClientData) {
        `wait while ready`()
        `fill firstname`(client.firstname)
        `fill lastname`(client.lastname)
        `select gender`(client.gender)
        `fill phone number`(client.phoneNumber)
        `select country`(client.country)
        registration.`click Next button`()
    }

    @Step("Wait for Step 2 elements ready")
    private fun `wait while ready`() {
        registration.`wait for step readiness`(STEP_2Name)
        firstnameField.shouldBe(visible)
        lastnameField.shouldBe(visible)
        genderOptionWrapper.should(exist)
        phoneNumberField.shouldBe(visible)
    }

    @Step("Fill firstname: {firstname}")
    private fun `fill firstname`(firstname: String) {
        firstnameField.`val`(firstname)
    }

    @Step("Fill firstname: {firstname}")
    private fun `fill lastname`(lastname: String) {
        lastnameField.`val`(lastname)
    }

    @Step("Select gender: {gender}")
    private fun `select gender`(gender: String) {
        `gender radio element`(gender)
            .shouldBe(visible)
            .click()
    }

    @Step("Fill phone number: {phoneNumber}")
    private fun `fill phone number`(phoneNumber: String) {
        phoneNumberField.`val`(phoneNumber)
    }

    @Step("Select birth date: {birthDate}")
    private fun `select date of birth`(birthDate: LocalDate) {
        dateOfBirthField.shouldBe(visible).click()
        datePicker.`select year`(birthDate)
        datePicker.`select month`(birthDate)
        datePicker.`select day`(birthDate)
    }

    @Step("Select country: {country}")
    private fun `select country`(countryName: String) {
        countryField.shouldBe(visible).click()
        `$`(byText(countryName)).click()
    }

    private fun `gender radio element`(gender: String): SelenideElement {
        return `$`("[value=${gender}]")
    }
}