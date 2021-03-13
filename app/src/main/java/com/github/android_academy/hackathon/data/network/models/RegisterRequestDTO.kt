package com.github.android_academy.hackathon.data.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterRequestDTO(
    val username: String,
    val pwd: String,
    val name: String,
    val isMentor: Boolean
)