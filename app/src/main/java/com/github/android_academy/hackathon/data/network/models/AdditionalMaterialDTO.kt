package com.github.android_academy.hackathon.data.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdditionalMaterialDTO(
    val topicName: String,
    val url: String
)