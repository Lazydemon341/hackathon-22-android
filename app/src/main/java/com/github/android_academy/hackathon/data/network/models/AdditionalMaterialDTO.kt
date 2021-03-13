package com.github.android_academy.hackathon.data.network.models

import com.github.android_academy.hackathon.domain.models.AdditionalMaterial
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdditionalMaterialDTO(
    val topicName: String,
    val url: String
)

fun AdditionalMaterialDTO.toAdditionalMaterial(): AdditionalMaterial =
    AdditionalMaterial(topicName = topicName, url = url)