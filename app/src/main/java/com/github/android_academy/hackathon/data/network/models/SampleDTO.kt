package com.github.android_academy.hackathon.data.network.models

import com.github.android_academy.hackathon.domain.models.Sample
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SampleDTO(
    val id: Long,
    val text: String
)

fun SampleDTO.toSample(): Sample =
    Sample(
        id = id,
        text = text
    )