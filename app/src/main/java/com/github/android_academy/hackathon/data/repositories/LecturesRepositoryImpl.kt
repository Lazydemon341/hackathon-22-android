package com.github.android_academy.hackathon.data.repositories

import com.github.android_academy.hackathon.data.network.ServerApi
import com.github.android_academy.hackathon.data.network.models.toLecture
import com.github.android_academy.hackathon.domain.models.Lecture
import com.github.android_academy.hackathon.domain.repositories.LecturesRepository
import javax.inject.Inject

class LecturesRepositoryImpl @Inject constructor(
    private val serverApi: ServerApi
) : LecturesRepository {

    override suspend fun getLecture(id: Long): Lecture =
        serverApi.getLectureById(lectureId = id)
            .toLecture()
}