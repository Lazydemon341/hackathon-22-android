package com.github.android_academy.hackathon.data.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequestDTO(
    val username: String,
    val pwd: String
)