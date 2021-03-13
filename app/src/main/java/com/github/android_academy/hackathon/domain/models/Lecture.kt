package com.github.android_academy.hackathon.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize()
data class Lecture(
    val id: Long? = null,
    val title: String,
    val youtubeUrl: String = "",
    val githubRepoUrl: String = "",
    val telegramChannel: String = "",
    val additionalMaterials: List<AdditionalMaterial>,
    val imgUrl: String? = null,
    val tags: List<String>,
    val courseId: Long
) : Parcelable