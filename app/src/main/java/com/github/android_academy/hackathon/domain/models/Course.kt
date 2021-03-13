package com.github.android_academy.hackathon.domain.models

data class Course(
    val id: Long = -1,
    val title: String,
    val shortDescription: String? = null,
    val fullDescription: String? = null,
    val imgUrl: String? = null,
    val tags: List<String>,
    val isSubscribed: Boolean = false
)