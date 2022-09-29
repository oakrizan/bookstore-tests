package com.demo

import com.demo.objects.client.ClientDTO
import com.demo.objects.client.ClientData
import com.demo.pages.registration.RegistrationStep1
import com.demo.pages.registration.RegistrationStep2
import com.demo.pages.registration.RegistrationStep3
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [Config::class])
@DisplayName("Demo Tests")
class DemoTests() {
    @Autowired
    private lateinit var clientDTO: ClientDTO
    @Autowired
    private lateinit var session: SessionManager
    @Autowired
    private lateinit var firstStep: RegistrationStep1
    @Autowired
    private lateinit var secondStep: RegistrationStep2
    @Autowired
    private lateinit var thirdStep: RegistrationStep3

    @Value("\${url}")
    lateinit var url: String

    @Test
    @DisplayName("New client registration")
    fun `register new client`() {
        var newClient: ClientData = clientDTO.adultClient()

        session.setupWith(url)
        firstStep.`do step 1`(newClient)
        secondStep.`do step 2`(newClient)
        thirdStep.`do step 3`(newClient)
        assertThat(thirdStep.`is success message visible`()).isTrue
    }
}