package com.github.android_academy.hackathon.domain.models

import com.github.android_academy.hackathon.data.network.models.AdditionalMaterialDTO

data class Lecture(
    val title: String,
    val youtubeUrl: String = "",
    val githubRepoUrl: String = "",
    val telegramChannel: String = "",
    val additionalMaterials: List<AdditionalMaterialDTO>,
    val imgUrl: String? = null,
    val tags: List<String>,
    val courseId: Long
)