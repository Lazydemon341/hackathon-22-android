package com.github.android_academy.hackathon.domain.repositories

import com.github.android_academy.hackathon.domain.models.Sample
import com.github.android_academy.hackathon.ui.ViewState

interface AuthRepository {
    suspend fun loadUser(): ViewState<Sample>

    suspend fun login(username: String, password: String): ViewState<Boolean>

    suspend fun register(
        username: String,
        password: String,
        name: String,
        isMentor: Boolean
    ): ViewState<Boolean>
}