package com.github.android_academy.hackathon.data.network

import com.github.android_academy.hackathon.data.network.models.LoginRequestDTO
import com.github.android_academy.hackathon.data.network.models.LoginResponseDTO
import com.github.android_academy.hackathon.data.network.models.RegisterRequestDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface ServerApi {
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequestDTO
    ): LoginResponseDTO

    @POST("register")
    suspend fun register(
        @Body registerRequestDTO: RegisterRequestDTO
    ): LoginResponseDTO
}