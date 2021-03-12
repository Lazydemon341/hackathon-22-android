package com.github.android_academy.hackathon.data.repositories

import com.github.android_academy.hackathon.data.network.models.SampleDTO
import com.github.android_academy.hackathon.data.network.models.toSample
import com.github.android_academy.hackathon.domain.models.Sample
import com.github.android_academy.hackathon.domain.repositories.SampleRepository
import javax.inject.Inject

class SampleRepositoryImpl @Inject constructor() : SampleRepository {
    override suspend fun loadSample(id: Long): Sample =
        SampleDTO(
            id = id,
            text = "Nice sample text"
        ).toSample()
}