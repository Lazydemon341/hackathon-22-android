package com.github.android_academy.hackathon.data.network

import com.github.android_academy.hackathon.data.network.models.SampleDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface ServerApi {
    @GET("sample/{id}")
    suspend fun getSample(
        @Path("id") id: Long
    ): SampleDTO
}