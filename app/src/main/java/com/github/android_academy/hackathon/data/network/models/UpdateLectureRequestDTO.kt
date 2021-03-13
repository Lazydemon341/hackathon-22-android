package com.github.android_academy.hackathon.data.network.models

import com.github.android_academy.hackathon.domain.models.Lecture
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateLectureRequestDTO(
    val id: Long? = null,
    val title: String,
    val youtubeUrl: String = "",
    val githubRepoUrl: String = "",
    val telegramChannel: String = "",
    val additionalMaterials: List<AdditionalMaterialDTO>,
    val imgUrl: String? = null,
    val tags: List<String>,
    val courseId: Long
)

fun UpdateLectureRequestDTO.toLecture(): Lecture =
    Lecture(
        title = title,
        youtubeUrl = youtubeUrl,
        githubRepoUrl = githubRepoUrl,
        telegramChannel = telegramChannel,
        additionalMaterials = additionalMaterials.map(AdditionalMaterialDTO::toAdditionalMaterial),
        imgUrl = imgUrl,
        tags = tags,
        courseId = courseId
    )