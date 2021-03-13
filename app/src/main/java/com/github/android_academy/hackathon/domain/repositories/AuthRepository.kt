package com.github.android_academy.hackathon.domain.repositories

import androidx.lifecycle.LiveData
import com.github.android_academy.hackathon.domain.OperationResult
import com.github.android_academy.hackathon.domain.models.User

interface AuthRepository {
    fun loadUser(): User?

    suspend fun login(username: String, password: String): OperationResult<Unit, String?>

    suspend fun register(
        username: String,
        password: String,
        name: String,
        isMentor: Boolean
    ): OperationResult<Unit, String?>

    fun observeUser(): LiveData<MyOptional<User>>

    fun logOut()

}

data class MyOptional<T : Any>(
    val value: T?
)