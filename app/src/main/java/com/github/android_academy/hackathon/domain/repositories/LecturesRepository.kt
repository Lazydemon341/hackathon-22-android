package com.github.android_academy.hackathon.domain.repositories

import com.github.android_academy.hackathon.domain.models.Lecture

interface LecturesRepository {
    suspend fun getLecture(id: Long): Lecture
}