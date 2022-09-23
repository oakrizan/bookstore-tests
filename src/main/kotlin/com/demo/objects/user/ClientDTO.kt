package com.demo.objects.user

import io.github.serpro69.kfaker.Faker
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.time.LocalDateTime.now
import kotlin.random.Random

@Component

class ClientDTO {
    private val faker = Faker()
    private val logger = KotlinLogging.logger {}

    fun adultClient(): ClientData {
        val id = randomID()
        val clientData = ClientData(
            username = "janeDoe$id",
            email = "janeDoe$id@email.com",
            password = id,
            firstname = "Jane",
            lastname = "Doe",
            gender = "male",
            dateOfBirth = faker.person.birthDate(45),
            phoneNumber = "45516895",
            country = faker.address.country(),
            aboutYourselfText = faker.bigBangTheory.quotes()
        )
        logger.info { "New user data: $clientData" }
        return clientData
    }

    private fun randomID(): String {
        return faker.code.asin()
    }
}