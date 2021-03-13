package com.github.android_academy.hackathon.data.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDTO(
    val username: String,
    val name: String,
    val mentor: Boolean
)
