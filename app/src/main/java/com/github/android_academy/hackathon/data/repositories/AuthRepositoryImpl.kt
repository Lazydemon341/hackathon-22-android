package com.github.android_academy.hackathon.data.repositories

import com.github.android_academy.hackathon.data.local.SampleDatabase
import com.github.android_academy.hackathon.domain.OperationResult
import com.github.android_academy.hackathon.domain.models.User
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(database: SampleDatabase) : AuthRepository {

    override fun loadUser(): User? {
        TODO("Not yet implemented")
    }

    override suspend fun login(
        username: String,
        password: String
    ): OperationResult<Unit, String> {
        TODO("Not yet implemented")
    }

    override suspend fun register(
        username: String,
        password: String,
        name: String,
        isMentor: Boolean
    ): OperationResult<Unit, String> {
        TODO("Not yet implemented")
    }
}