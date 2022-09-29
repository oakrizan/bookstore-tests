package com.demo.objects.client

import java.time.LocalDate

data class ClientData(
    val username: String,
    val email: String,
    val password: String,
    val firstname: String,
    val lastname: String,
    val gender: String,
    val phoneNumber: String,
    val dateOfBirth: LocalDate,
    val country: String,
    val aboutYou: String
)