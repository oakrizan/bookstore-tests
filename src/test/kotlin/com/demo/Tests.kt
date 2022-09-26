package com.demo

import com.demo.objects.user.ClientDTO
import com.demo.objects.user.ClientData
import com.demo.pages.Registration
import com.demo.pages.Registration.Companion.SUCCESS_REGISTRATION_TEXT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [Config::class])
class Tests() {
    @Autowired
    private lateinit var registration: Registration
    @Autowired
    private lateinit var clientDTO: ClientDTO
    @Autowired
    private lateinit var session: SessionManager

    @Value("\${url}")
    lateinit var url: String

    @Test
    fun `test`() {
        var newClient: ClientData = clientDTO.adultClient()

        url = "https://demo.wpeverest.com/user-registration/multi-step-form/"
        session.setupWith(url)
        registration.`do step 1`(newClient)
        registration.`do step 2`(newClient)
        registration.`do step 3`(newClient)
        assertThat(registration.`registration success message`().text().contentEquals(SUCCESS_REGISTRATION_TEXT)).isTrue()
    }
}