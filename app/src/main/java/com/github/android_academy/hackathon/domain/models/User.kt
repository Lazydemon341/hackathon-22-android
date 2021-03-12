package com.github.android_academy.hackathon.domain.models

data class User(
    val username : String,
    val name : String,
    val isMentor : Boolean,
    val token : String
)