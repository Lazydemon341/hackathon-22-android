package com.github.android_academy.hackathon.domain.repositories

import com.github.android_academy.hackathon.domain.models.Sample

interface SampleRepository {
    suspend fun loadSample(id: Long): Sample
}