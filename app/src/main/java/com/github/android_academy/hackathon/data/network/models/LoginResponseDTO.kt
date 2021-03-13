package com.github.android_academy.hackathon.data.network.models

import com.github.android_academy.hackathon.domain.models.User
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponseDTO(
    val userProfile: UserDTO,
    val token: String
)

fun LoginResponseDTO.toUser(): User =
    User(
        username = userProfile.username,
        name = userProfile.name,
        isMentor = userProfile.mentor,
        token = token
    )