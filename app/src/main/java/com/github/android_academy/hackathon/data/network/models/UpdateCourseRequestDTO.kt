package com.github.android_academy.hackathon.data.network.models

import com.github.android_academy.hackathon.domain.models.Course
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateCourseRequestDTO(
    val id: Long? = null,
    val title: String,
    val shortDescription: String? = null,
    val fullDescription: String? = null,
    val imgUrl: String? = null,
    val tags: List<String>,
    val isSubscribed: Boolean
)

fun UpdateCourseRequestDTO.toCourse(): Course =
    Course(
        title = title,
        shortDescription = shortDescription,
        fullDescription = fullDescription,
        imgUrl = imgUrl,
        tags = tags,
        isSubscribed = isSubscribed
    )