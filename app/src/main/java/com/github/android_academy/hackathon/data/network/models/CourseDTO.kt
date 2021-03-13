package com.github.android_academy.hackathon.data.network.models

import com.github.android_academy.hackathon.domain.models.Course
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CourseDTO(
    val id: Long,
    val title: String,
    val shortDescription: String? = null,
    val fullDescription: String? = null,
    val imgUrl: String? = null,
    val tags: List<String>,
    val isSubscribed: Boolean
)

fun CourseDTO.toCourse(): Course =
    Course(
        id = id,
        title = title,
        shortDescription = shortDescription,
        fullDescription = fullDescription,
        imgUrl = imgUrl,
        tags = tags,
        isSubscribed = isSubscribed
    )