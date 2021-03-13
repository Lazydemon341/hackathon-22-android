package com.github.android_academy.hackathon.domain.repositories

import com.github.android_academy.hackathon.domain.OperationResult
import com.github.android_academy.hackathon.domain.models.User
import com.github.android_academy.hackathon.ui.ViewState

interface AuthRepository {
    fun loadUser(): User?

    suspend fun login(username: String, password: String): OperationResult<User, String?>

    suspend fun register(
        username: String,
        password: String,
        name: String,
        isMentor: Boolean
    ): OperationResult<User, String?>
}