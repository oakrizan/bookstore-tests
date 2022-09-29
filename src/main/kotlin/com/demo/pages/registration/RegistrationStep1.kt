package com.demo.pages.registration

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.SelenideElement
import com.demo.objects.client.ClientData
import com.demo.pages.registration.StepName.STEP_1Name
import io.qameta.allure.Step
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RegistrationStep1 {
    private val usernameField: SelenideElement = Selenide.`$`("#user_login")
    private val emailField: SelenideElement = Selenide.`$`("#user_email")
    private val passwordField: SelenideElement = Selenide.`$`("#user_pass")
    private val confirmPasswordField: SelenideElement = Selenide.`$`("#user_confirm_password")

    @Autowired
    private lateinit var registration: RegistrationCommon

    @Step("Provide data for 1st step")
    fun `do step 1`(client: ClientData) {
        `wait while ready`()
        `fill username`(client.username)
        `fill email`(client.email)
        `fill password`(client.password)
        `confirm password`(client.password)
        registration.`click Next button`()
    }

    @Step("Wait for Step 1 elements ready")
    private fun `wait while ready`() {
        registration.`wait for step readiness`(STEP_1Name)
        usernameField.shouldBe(visible)
        emailField.shouldBe(visible)
        passwordField.shouldBe(visible)
        confirmPasswordField.shouldBe(visible)
    }

    @Step("Fill username: {username}")
    private fun `fill username`(username: String) {
        usernameField.`val`(username)
    }

    @Step("Fill email: {email}")
    private fun `fill email`(email: String) {
        emailField.`val`(email)
    }

    @Step("Fill password: {password}")
    private fun `fill password`(password: String) {
        passwordField.`val`(password)
    }

    @Step("Confirm password: {password}")
    private fun `confirm password`(password: String) {
        confirmPasswordField.`val`(password)
    }
}