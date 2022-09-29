package com.demo.pages.registration

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import com.demo.objects.client.ClientData
import com.demo.pages.registration.StepName.STEP_3Name
import io.qameta.allure.Step
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RegistrationStep3 {
    companion object {
        const val SUCCESS_REGISTRATION_TEXT = "User successfully registered."
    }

    private val successMessage: SelenideElement = `$`("#ur-submit-message-node")
    private val displayNameField: SelenideElement = `$`("#display_name")
    private val aboutYouField: SelenideElement = `$`("[data-label\$=\"about yourself\"]")
    private val privacyPolicyRadio: SelenideElement = `$`("[data-label=\"Privacy Policy\"] input")

    @Autowired
    private lateinit var registration: RegistrationCommon

    @Step("Provide data for 3rd step")
    fun `do step 3`(client: ClientData) {
        `wait while ready`()
        `fill display name field`(client.username)
        `fill about you field`(client.aboutYou)
        `accept privacy policy`()
        registration.`click Submit button`()
    }

    @Step("Provide Display name")
    private fun `fill display name field`(displayName: String) {
        displayNameField.`val`(displayName)
    }

    @Step("Provide About you info")
    private fun `fill about you field`(aboutYouText: String) {
        aboutYouField.`val`(aboutYouText)
    }

    @Step("Accept Privacy policy")
    private fun `accept privacy policy`() {
        privacyPolicyRadio.click()
    }

    @Step("Chek if registration successful")
    fun `is success message visible`(): Boolean {
        return successMessage
            .shouldBe(visible)
            .has(text(SUCCESS_REGISTRATION_TEXT))
    }

    @Step("Wait for Step 3 elements ready")
    private fun `wait while ready`() {
        registration.`wait for step readiness`(STEP_3Name)
        displayNameField.shouldBe(visible)
        aboutYouField.shouldBe(visible)
        privacyPolicyRadio.shouldBe(visible)
    }
}