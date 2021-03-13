package com.github.android_academy.hackathon.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize()
data class AdditionalMaterial(
    val topicName: String,
    val url: String
) : Parcelable