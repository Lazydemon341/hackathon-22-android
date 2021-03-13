package com.github.android_academy.hackathon.domain.models

data class Course(
        val name:String,
        val lecturesCount:Int,
        val url : String,
        val lectureList: List<Lecture>
) {
}