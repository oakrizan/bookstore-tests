package com.demo.pages.registration

import com.codeborne.selenide.Condition.*
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement
import org.springframework.stereotype.Component

@Component
class RegistrationCommon {
    private val header: SelenideElement = `$`("#main h1")
    private val stepBar: SelenideElement = `$`(".user-registration-multi-part--steps-list")
    private val activeStep: String = ".active"
    private val nextButton: SelenideElement = `$`("[data-action=\"next\"]")
    private val submitButton: SelenideElement = `$`(".ur-button-container [type=\"submit\"]")

    fun `wait for step readiness`(step: StepName) {
        `wait while commons ready`()
        stepBar.`$$`(activeStep)
            .last()
            .shouldHave(text(step.text))
    }

    fun `click Next button`() {
        `wait while commons ready`()
        nextButton.shouldBe(visible, enabled).click()
    }

    fun `click Submit button`() {
        `wait while commons ready`()
        submitButton.shouldBe(visible, enabled).click()
    }

    private fun `wait while commons ready`() {
        header.shouldBe(visible)
            .shouldHave(text("Multi Step Form"))
        stepBar.shouldBe(visible)
    }
}