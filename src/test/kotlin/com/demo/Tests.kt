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
//        Configuration.browserSize = "2100x1080"
        session.setupWith(url)

    }
}