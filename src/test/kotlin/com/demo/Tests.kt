package com.demo

import com.demo.objects.user.ClientDTO
import com.demo.pages.Registration
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
        url = "https://demo.wpeverest.com/user-registration/multi-step-form/"
        session.setupWith(url)
        registration.`wait while ready`()
    }
}