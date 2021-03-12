package com.github.android_academy.hackathon.data.repositories

import androidx.lifecycle.LiveData
import com.github.android_academy.hackathon.data.local.SampleDatabase
import com.github.android_academy.hackathon.data.network.models.SampleDTO
import com.github.android_academy.hackathon.data.network.models.toSample
import com.github.android_academy.hackathon.domain.models.Sample
import com.github.android_academy.hackathon.domain.repositories.AuthRepository
import com.github.android_academy.hackathon.ui.ViewState
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(database : SampleDatabase) : AuthRepository {

    override suspend fun loadUser(): ViewState<Sample> {
        TODO("Not yet implemented")
    }

    override suspend fun login(username: String, password: String): ViewState<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun register(
        username: String,
        password: String,
        name: String,
        isMentor: Boolean
    ): ViewState<Boolean> {
        TODO("Not yet implemented")
    }
}