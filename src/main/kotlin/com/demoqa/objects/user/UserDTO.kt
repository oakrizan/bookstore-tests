package com.demoqa.objects.user

import io.github.serpro69.kfaker.Faker
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component

class UserDTO {
    private val faker = Faker()
    private val logger = KotlinLogging.logger {}

    fun newUser(): UserData {
        val userData =  UserData(
            firstname = "Jane",
            lastname = "Doe",
            username = "janeDoe" + randomID(),
            password = "Secure" + randomID()
        )
        logger.info { "New user data: $userData" }
        return userData
    }

    private fun randomID(): String {
        return faker.random.nextInt().toString()
    }
}